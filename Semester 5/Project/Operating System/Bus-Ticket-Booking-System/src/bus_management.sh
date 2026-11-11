
#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DATA_FILE="$SCRIPT_DIR/data/buses.txt"

mkdir -p "$SCRIPT_DIR/data"
touch "$DATA_FILE"

bus_exists() {
    local search_bus="$1"
    local search_date="$2"
    local bus departure destination journey_date fare seats

    while IFS='|' read -r bus departure destination journey_date fare seats; do
        if [[ "$bus" == "$search_bus" && "$journey_date" == "$search_date" ]]; then
            return 0
        fi
    done < "$DATA_FILE"

    return 1
}

valid_date() {
    local input="$1"

    [[ "$input" =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2}$ ]] || return 1

    date -d "$input" "+%Y-%m-%d" 2>/dev/null | grep -qx "$input"
}

valid_fare() {
    [[ "$1" =~ ^[0-9]+([.][0-9]{1,2})?$ ]] &&
    [[ "$1" =~ [1-9] ]]
}

add_bus() {
    clear
    echo "========== ADD NEW BUS =========="

    local bus departure destination journey_date fare

    read -rp "Bus Number: " bus
    read -rp "Departure: " departure
    read -rp "Destination: " destination
    read -rp "Journey Date (YYYY-MM-DD): " journey_date
    read -rp "Ticket Fare (BDT): " fare

    if [[ -z "$bus" || -z "$departure" || -z "$destination" ||
          -z "$journey_date" || -z "$fare" ]]; then
        echo "All fields are required!"
        read -rp "Press Enter..."
        return
    fi

    if [[ "$bus$departure$destination" == *"|"* ]]; then
        echo "The | character is not allowed!"
        read -rp "Press Enter..."
        return
    fi

    if [[ "$departure" == "$destination" ]]; then
        echo "Departure and destination cannot be the same!"
        read -rp "Press Enter..."
        return
    fi

    if ! valid_date "$journey_date"; then
        echo "Invalid journey date!"
        read -rp "Press Enter..."
        return
    fi

    if ! valid_fare "$fare"; then
        echo "Invalid fare!"
        read -rp "Press Enter..."
        return
    fi

    if bus_exists "$bus" "$journey_date"; then
        echo "This bus already has a schedule on this date!"
        read -rp "Press Enter..."
        return
    fi

    echo "$bus|$departure|$destination|$journey_date|$fare|45" >> "$DATA_FILE"

    echo
    echo "Bus added successfully!"
    echo "Total seats: 45"

    read -rp "Press Enter..."
}

view_all_buses() {
    clear
    echo "================ ALL BUSES ================"

    if [[ ! -s "$DATA_FILE" ]]; then
        echo "No buses registered!"
    else
        printf "%-12s %-15s %-15s %-15s %-10s %-8s\n" \
        "BUS" "DEPARTURE" "DESTINATION" "DATE" "FARE" "SEATS"

        echo "--------------------------------------------------------------------------"

        while IFS='|' read -r bus departure destination journey_date fare seats; do
            printf "%-12s %-15s %-15s %-15s %-10s %-8s\n" \
            "$bus" "$departure" "$destination" "$journey_date" "$fare" "$seats"
        done < "$DATA_FILE"
    fi

    echo
    read -rp "Press Enter..."
}

update_bus() {
    clear
    echo "========== UPDATE BUS =========="

    local search_bus search_date
    local bus departure destination journey_date fare seats
    local new_departure new_destination new_date new_fare
    local temp_file="$DATA_FILE.tmp"
    local found=0

    read -rp "Bus Number: " search_bus
    read -rp "Current Journey Date (YYYY-MM-DD): " search_date

    if ! bus_exists "$search_bus" "$search_date"; then
        echo "Bus schedule not found!"
        read -rp "Press Enter..."
        return
    fi

    read -rp "New Departure: " new_departure
    read -rp "New Destination: " new_destination
    read -rp "New Journey Date: " new_date
    read -rp "New Fare: " new_fare

    if [[ -z "$new_departure" ]]; then
        new_departure=""
    fi

    : > "$temp_file"

    while IFS='|' read -r bus departure destination journey_date fare seats; do

        if [[ "$bus" == "$search_bus" && "$journey_date" == "$search_date" ]]; then

            found=1

            new_departure="${new_departure:-$departure}"
            new_destination="${new_destination:-$destination}"
            new_date="${new_date:-$journey_date}"
            new_fare="${new_fare:-$fare}"

            if [[ "$new_departure$new_destination" == *"|"* ]] ||
               [[ "$new_departure" == "$new_destination" ]] ||
               ! valid_date "$new_date" ||
               ! valid_fare "$new_fare" ||
               bus_exists "$bus" "$new_date" && [[ "$new_date" != "$search_date" ]]; then

                echo "Invalid information or duplicate schedule!"
                rm -f "$temp_file"
                read -rp "Press Enter..."
                return
            fi

            echo "$bus|$new_departure|$new_destination|$new_date|$new_fare|45" >> "$temp_file"

        else
            echo "$bus|$departure|$destination|$journey_date|$fare|$seats" >> "$temp_file"
        fi

    done < "$DATA_FILE"

    if [[ "$found" -eq 1 ]]; then
        mv "$temp_file" "$DATA_FILE"
        echo "Bus updated successfully!"
    else
        rm -f "$temp_file"
        echo "Bus not found!"
    fi

    read -rp "Press Enter..."
}

delete_bus() {
    clear
    echo "========== DELETE BUS =========="

    local search_bus search_date confirmation
    local bus departure destination journey_date fare seats
    local found=0
    local temp_file="$DATA_FILE.tmp"

    read -rp "Bus Number: " search_bus
    read -rp "Journey Date (YYYY-MM-DD): " search_date

    if ! bus_exists "$search_bus" "$search_date"; then
        echo "Bus schedule not found!"
        read -rp "Press Enter..."
        return
    fi

    # Do not delete a schedule with confirmed bookings.
    if grep -Fq "|$search_bus|$search_date|" "$SCRIPT_DIR/data/bookings.txt" 2>/dev/null; then
        echo "Cannot delete: booking records exist for this schedule."
        read -rp "Press Enter..."
        return
    fi

    read -rp "Confirm deletion? (y/n): " confirmation

    if [[ "$confirmation" != "y" && "$confirmation" != "Y" ]]; then
        echo "Deletion cancelled."
        read -rp "Press Enter..."
        return
    fi

    : > "$temp_file"

    while IFS='|' read -r bus departure destination journey_date fare seats; do
        if [[ "$bus" != "$search_bus" || "$journey_date" != "$search_date" ]]; then
            echo "$bus|$departure|$destination|$journey_date|$fare|$seats" >> "$temp_file"
        else
            found=1
        fi
    done < "$DATA_FILE"

    mv "$temp_file" "$DATA_FILE"

    if [[ "$found" -eq 1 ]]; then
        echo "Bus deleted successfully!"
    else
        echo "Bus not found!"
    fi

    read -rp "Press Enter..."
}

bus_management_menu() {
    while true; do
        clear

        echo "================================"
        echo "        BUS MANAGEMENT"
        echo "================================"
        echo "1. Add New Bus"
        echo "2. Update Bus"
        echo "3. Delete Bus"
        echo "4. View All Buses"
        echo "5. Back to Main Menu"
        echo "================================"

        read -rp "Enter choice: " choice

        case "$choice" in
            1) add_bus ;;
            2) update_bus ;;
            3) delete_bus ;;
            4) view_all_buses ;;
            5) return ;;
            *) echo "Invalid choice!"; sleep 1 ;;
        esac
    done
}
