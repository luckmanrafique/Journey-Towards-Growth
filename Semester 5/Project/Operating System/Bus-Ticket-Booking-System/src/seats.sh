
#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BOOKING_FILE="$SCRIPT_DIR/data/bookings.txt"

# Check whether a seat is already booked
seat_is_sold() {
    local search_bus="$1"
    local search_date="$2"
    local search_seat="$3"

    local ticket bus journey_date passenger phone seats fare status
    local seat

    while IFS='|' read -r ticket bus journey_date passenger phone seats fare status; do

        if [[ "$bus" == "$search_bus" &&
              "$journey_date" == "$search_date" &&
              "$status" == "CONFIRMED" ]]; then

            IFS=',' read -ra booked_seats <<< "$seats"

            for seat in "${booked_seats[@]}"; do
                if [[ "$seat" == "$search_seat" ]]; then
                    return 0
                fi
            done
        fi

    done < "$BOOKING_FILE"

    return 1
}

# Display the bus seat layout
display_seat_layout() {

    local bus="$1"
    local journey_date="$2"
    local row number seat

    echo
    echo "=============================================="
    echo "                BUS SEAT LAYOUT"
    echo "=============================================="

    echo "Bus: $bus"
    echo "Journey Date: $journey_date"
    echo

    echo "                    [ EX-1 ]"
    echo "                  FRONT / DRIVER"
    echo
    echo "[Available] [Sold]"
    echo

    for row in A B C D E F G H I J; do

        printf "       "

        for number in 1 2 3 4; do

            seat="${row}${number}"

            if seat_is_sold "$bus" "$journey_date" "$seat"; then
                printf "[XX] "
            else
                printf "[%s] " "$seat"
            fi

        done

        echo

    done

    printf "     "

    for number in 1 2 3 4 5; do

        seat="K${number}"

        if seat_is_sold "$bus" "$journey_date" "$seat"; then
            printf "[XX] "
        else
            printf "[%s] " "$seat"
        fi

    done

    echo
    echo
    echo "XX = Sold"
    echo "Seat labels = Available"
    echo "=============================================="
}

# Select seats for booking
select_seats() {

    local bus="$1"
    local journey_date="$2"

    local input seat
    local count=0
    local duplicate
    local valid
    local -a selected=()

    display_seat_layout "$bus" "$journey_date"

    echo "Maximum 6 seats can be selected."
    echo "Example: A1,A2,B3"

    read -rp "Enter seat numbers: " input

    if [[ -z "$input" || "$input" == *"|"* ]]; then
        echo "Invalid seat selection!"
        return 1
    fi

    IFS=',' read -ra requested_seats <<< "$input"

    for seat in "${requested_seats[@]}"; do

        seat="${seat//[[:space:]]/}"
        valid=0

        for row in A B C D E F G H I J; do
            for number in 1 2 3 4; do
                if [[ "$seat" == "${row}${number}" ]]; then
                    valid=1
                fi
            done
        done

        for number in 1 2 3 4 5; do
            if [[ "$seat" == "K${number}" ]]; then
                valid=1
            fi
        done

        if [[ "$valid" -ne 1 ]]; then
            echo "Invalid seat: $seat"
            return 1
        fi

        if seat_is_sold "$bus" "$journey_date" "$seat"; then
            echo "Seat $seat is already sold!"
            return 1
        fi

        duplicate=0

        for existing in "${selected[@]}"; do
            if [[ "$existing" == "$seat" ]]; then
                duplicate=1
            fi
        done

        if [[ "$duplicate" -eq 1 ]]; then
            echo "Duplicate seat: $seat"
            return 1
        fi

        selected+=("$seat")
        ((count++))

    done

    if [[ "$count" -gt 6 ]]; then
        echo "You can select a maximum of 6 seats!"
        return 1
    fi

    SELECTED_SEATS=$(IFS=,; echo "${selected[*]}")

    echo
    echo "Selected Seats: $SELECTED_SEATS"

    return 0
}
