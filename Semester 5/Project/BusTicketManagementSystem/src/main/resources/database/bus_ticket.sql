
CREATE DATABASE bus_ticket_db;

USE bus_ticket_db;

-- 1. Users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'STAFF') NOT NULL DEFAULT 'STAFF',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Buses
CREATE TABLE buses (
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_name VARCHAR(100) NOT NULL,
    bus_number VARCHAR(30) NOT NULL UNIQUE,
    bus_type VARCHAR(50),
    seat_capacity INT NOT NULL DEFAULT 46,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- 3. Routes
CREATE TABLE routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    distance DECIMAL(10,2),
    fare DECIMAL(10,2) NOT NULL,
    UNIQUE KEY unique_route (source, destination)
);

-- 4. Trips
CREATE TABLE trips (
    trip_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    route_id INT NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    trip_status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED')
        DEFAULT 'SCHEDULED',

    FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
    FOREIGN KEY (route_id) REFERENCES routes(route_id)
);

-- 5. Bus Seats
CREATE TABLE bus_seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,

    FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
    UNIQUE KEY unique_bus_seat (bus_id, seat_number)
);

-- 6. Bookings
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_number VARCHAR(40) NOT NULL UNIQUE,
    trip_id INT NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    seat_count INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    booking_status ENUM('CONFIRMED', 'CANCELLED')
        DEFAULT 'CONFIRMED',
    booked_by INT NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (trip_id) REFERENCES trips(trip_id),
    FOREIGN KEY (booked_by) REFERENCES users(user_id)
);

-- 7. Booking Seats
CREATE TABLE booking_seats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    trip_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,

    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (trip_id) REFERENCES trips(trip_id),

    UNIQUE KEY unique_trip_seat (trip_id, seat_number)
);

-- 8. Payments
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method ENUM('CASH', 'CARD', 'MOBILE_BANKING')
        DEFAULT 'CASH',
    payment_status ENUM('PAID', 'REFUNDED', 'PENDING')
        DEFAULT 'PAID',
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);
