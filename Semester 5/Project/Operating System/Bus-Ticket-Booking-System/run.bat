
@echo off
title Bus Ticket Booking System

cd /d "%~dp0"

if not exist "src\main.sh" (
    echo ERROR: src\main.sh not found!
    pause
    exit /b 1
)

if not exist "data" mkdir "data"
if not exist "tickets" mkdir "tickets"

if not exist "data\buses.txt" type nul > "data\buses.txt"
if not exist "data\bookings.txt" type nul > "data\bookings.txt"

set "GIT_BASH=C:\Program Files\Git\bin\bash.exe"

if not exist "%GIT_BASH%" (
    echo Git Bash not found at the default location!
    echo Please check your Git installation.
    pause
    exit /b 1
)

"%GIT_BASH%" --login -i -c "cd '%CD:\=/%' && bash src/main.sh"

pause
