
#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

BUS_FILE="$SCRIPT_DIR/data/buses.txt"
BOOKING_FILE="$SCRIPT_DIR/data/bookings.txt"
TICKET_DIR="$SCRIPT_DIR/tickets"

source "$SCRIPT_DIR/seats.sh"
source "$SCRIPT_DIR/pdf_generator.sh"

book_ticket() {

    clear

    echo "======================================"
    echo "             BOOK A TICKET"
    echo "======================================"

    if [[ ! -s "$BUS_FILE" ]]; then
        echo "No buses available!"
        read -rp "Press Enter..."
        return
    fi

    echo
    echo "Available Bus Schedules:"
    echo

    local bus departure destination journey_date fare total_seats

    printf "%-12s %-15s %-15s %-15s %-10s\n" \
    "BUS" "DEPARTURE" "DESTINATION" "DATE" "FARE"

    echo "----------------------------------------------------------------"

    while IFS='|' read -r bus departure destination journey_date fare total_seats; do
        printf "%-12s %-15s %-15s %-15s %-10s\n" \
        "$bus" "$departure" "$destination" "$journey_date" "$fare"
    done < "$BUS_FILE"

    echo
    read -rp "Enter Bus Number: " selected_bus
    read -rp "Enter Journey Date: " selected_date

    local selected_departure=""
    local selected_destination=""
    local selected_fare=""
    local found=0

    while IFS='|' read -r bus departure destination journey_date fare total_seats; do

        if [[ "$bus" == "$selected_bus" &&
              "$journey_date" == "$selected_date" ]]; then

            selected_departure="$departure"
            selected_destination="$destination"
            selected_fare="$fare"
            found=1
            break
        fi

    done < "$BUS_FILE"

    if [[ "$found" -ne 1 ]]; then
        echo "Bus schedule not found!"
        read -rp "Press Enter..."
        return
    fi

    # Select seats
    if ! select_seats "$selected_bus" "$selected_date"; then
        read -rp "Press Enter..."
        return
    fi

    local passenger phone
    local seat_count total_fare
    local ticket_id pdf_file

    echo
    echo "========== PASSENGER DETAILS =========="

    read -rp "Passenger Name: " passenger
    read -rp "Phone Number: " phone

    if [[ -z "$passenger" || -z "$phone" ||
          "$passenger" == *"|"* || "$phone" == *"|"* ||
          "$passenger" == *"("* || "$passenger" == *")"* ||
          "$phone" == *"("* || "$phone" == *")"* ]]; then

        echo "Invalid passenger information!"
        read -rp "Press Enter..."
        return
    fi

    local -a seats_array
    IFS=',' read -ra seats_array <<< "$SELECTED_SEATS"

    seat_count="${#seats_array[@]}"

    total_fare=$(awk -v fare="$selected_fare" \
        -v count="$seat_count" 'BEGIN {printf "%.2f", fare * count}')

    echo
    echo "========== BOOKING SUMMARY =========="
    echo "Passenger: $passenger"
    echo "Phone: $phone"
    echo "Bus: $selected_bus"
    echo "Departure: $selected_departure"
    echo "Destination: $selected_destination"
    echo "Journey Date: $selected_date"
    echo "Seats: $SELECTED_SEATS"
    echo "Number of Seats: $seat_count"
    echo "Total Fare: $total_fare BDT"
    echo "====================================="

    local confirmation

    read -rp "Confirm Booking? (y/n): " confirmation

    if [[ "$confirmation" != "y" && "$confirmation" != "Y" ]]; then
        echo "Booking cancelled!"
        read -rp "Press Enter..."
        return
    fi

    # Recheck availability before saving
    local seat

    for seat in "${seats_array[@]}"; do
        if seat_is_sold "$selected_bus" "$selected_date" "$seat"; then
            echo "Booking failed! Seat $seat is no longer available."
            read -rp "Press Enter..."
            return
        fi
    done

    mkdir -p "$TICKET_DIR"

    ticket_id="TKT-$(date +%Y%m%d%H%M%S)-$$"

    # Save booking information
    echo "$ticket_id|$selected_bus|$selected_date|$passenger|$phone|$SELECTED_SEATS|$total_fare|CONFIRMED" >> "$BOOKING_FILE"

    pdf_file="$TICKET_DIR/$ticket_id.pdf"

    if generate_ticket_pdf "$pdf_file" \
        "$ticket_id" \
        "$passenger" \
        "$phone" \
        "$selected_bus" \
        "$selected_departure" \
        "$selected_destination" \
        "$selected_date" \
        "$SELECTED_SEATS" \
        "$total_fare"; then

        echo
        echo "======================================"
        echo "       BOOKING SUCCESSFUL!"
        echo "======================================"
        echo "Ticket ID: $ticket_id"
        echo "PDF Ticket: $pdf_file"
        echo "======================================"

    else
        echo "Booking saved, but PDF generation failed."
        echo "Ticket ID: $ticket_id"
    fi

    read -rp "Press Enter..."
}

view_booking_history() {

    clear

    echo "======================================"
    echo "            BOOKING HISTORY"
    echo "======================================"

    if [[ ! -s "$BOOKING_FILE" ]]; then
        echo "No booking records found!"
    else

        printf "%-25s %-12s %-12s %-18s %-12s %-15s %-10s %-12s\n" \
        "TICKET ID" "BUS" "DATE" "PASSENGER" "PHONE" "SEATS" "FARE" "STATUS"

        echo "--------------------------------------------------------------------------"

        local ticket bus journey_date passenger phone seats fare status

        while IFS='|' read -r ticket bus journey_date passenger phone seats fare status; do

            printf "%-25s %-12s %-12s %-18s %-12s %-15s %-10s %-12s\n" \
            "$ticket" "$bus" "$journey_date" "$passenger" "$phone" "$seats" "$fare" "$status"

        done < "$BOOKING_FILE"

    fi

    echo
    read -rp "Press Enter..."
}

cancel_booking() {

    clear

    echo "======================================"
    echo "            CANCEL BOOKING"
    echo "======================================"

    local search_ticket
    local ticket bus journey_date passenger phone seats fare status
    local found=0
    local temp_file="$BOOKING_FILE.tmp"

    read -rp "Enter Ticket ID: " search_ticket

    : > "$temp_file"

    while IFS='|' read -r ticket bus journey_date passenger phone seats fare status; do

        if [[ "$ticket" == "$search_ticket" && "$status" == "CONFIRMED" ]]; then

            found=1
            echo "$ticket|$bus|$journey_date|$passenger|$phone|$seats|$fare|CANCELLED" >> "$temp_file"

        else

            echo "$ticket|$bus|$journey_date|$passenger|$phone|$seats|$fare|$status" >> "$temp_file"

        fi

    done < "$BOOKING_FILE"

    mv "$temp_file" "$BOOKING_FILE"

    if [[ "$found" -eq 1 ]]; then
        echo "Booking cancelled successfully!"
        echo "Seats are now available again."
    else
        echo "Active booking not found!"
    fi

    read -rp "Press Enter..."
}
