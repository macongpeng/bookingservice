# Booking Service

## Overview

This project is a backend booking system developed using Spring Boot and Kotlin. It allows customers to sign up and activate their accounts via email to register themselves. Additionally, the system provides functionalities for administrators to add and manage customers and vehicles. The system is designed to handle bookings efficiently through various endpoints.

## Features

- **Customer Registration:** Customers can sign up and activate their accounts using their email.
- **Admin Management:** Administrators can add new customers and manage existing ones.
- **Vehicle Management:** Administrators can add and manage vehicles in the system.
- **Booking Management:** The system allows for the creation, modification, and management of bookings through dedicated endpoints.

## Getting Started

### Prerequisites

- JDK 21 or higher
- Gradle
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone the repository:

git clone https://github.com/macongpeng/bookingservice.git

2. Navigate to the project directory:

cd bookingservice

3. Build the project with Gradle:

./gradlew build


### Running the Application

Run the application using Gradle:

./gradlew bootRun


## Usage

### Endpoints

- `POST /signup` - Register a new customer.
- `POST /admin/add-customer` - Add a customer by admin.
- `POST /admin/add-vehicle` - Add a vehicle to the system.
- `POST /bookings` - Create a new booking.
- Additional endpoints for managing customers, vehicles, and bookings.

## Contributing

Contributions to the project are welcome. Please follow the standard fork and pull request workflow.

## License

This project is licensed under the Apache License, Version 2.0 (Apache-2.0) - see the LICENSE file for details.