
#!/bin/bash

# ==========================================
# BUS TICKET BOOKING MANAGEMENT SYSTEM
# MAIN APPLICATION
# ==========================================

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Create required directories and files
mkdir -p "$PROJECT_DIR/data"
mkdir -p "$PROJECT_DIR/tickets"

touch "$PROJECT_DIR/data/buses.txt"
touch "$PROJECT_DIR/data/bookings.txt"

# Load application modules
source "$PROJECT_DIR/pdf_generator.sh"
source "$PROJECT_DIR/bus_management.sh"
source "$PROJECT_DIR/seats.sh"
source "$PROJECT_DIR/booking.sh"

# Terminal colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m'

# Main menu
main_menu() {

    while true; do

        clear

        echo -e "${CYAN}"
        echo "=========================================="
        echo "       BUS TICKET BOOKING SYSTEM"
        echo "=========================================="
        echo -e "${NC}"

        echo "1. Bus Management"
        echo "2. Book a Ticket"
        echo "3. View Available Buses"
        echo "4. Booking History"
        echo "5. Cancel Booking"
        echo "6. Exit"

        echo "=========================================="

        read -rp "Enter your choice: " choice

        case "$choice" in

            1)
                bus_management_menu
                ;;

            2)
                book_ticket
                ;;

            3)
                view_all_buses
                ;;

            4)
                view_booking_history
                ;;

            5)
                cancel_booking
                ;;

            6)
                clear
                echo -e "${GREEN}Thank you for using our system!${NC}"
                exit 0
                ;;

            *)
                echo -e "${RED}Invalid choice!${NC}"
                sleep 1
                ;;

        esac

    done
}

main_menu
