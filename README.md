# Account Service

Account Service is a Spring Boot microservice that provides account management features such as creating, retrieving, and deleting user accounts. This service is developed with a REST API and follows TDD (Test-Driven Development) practices.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [API Endpoints](#api-endpoints)
- [Error Codes](#error-codes)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java 17
- Spring Boot 3.3.4
- Gradle 7.x
- JUnit 5
- Mockito
- H2 Database (for testing)

## Installation

1. Clone the repository:
   - `git clone https://github.com/your-username/account-service.git`
   
2. Navigate to the project directory:
   - `cd account-service`
   
3. Build the project using Gradle:
   - `./gradlew build`

## Running the Application

To run the application locally, use the following command:
- `./gradlew bootRun`

The service will be accessible at `http://localhost:8080`.

## Running Tests

This project follows a TDD approach. To run all unit tests, execute the following command:
- `./gradlew test`

## API Endpoints

### Get Account by ID

- **URL:** `/accounts/{accountId}`
- **Method:** `GET`
- **Response:**
  ```json
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "balance": 100.50
  }

### Create Account

- **URL:** /accounts
- **Method:** POST
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "initialBalance": 100.50
  }
  
- **Response:**
 ```json
   {
     "id": "1",
     "name": "John Doe",
     "email": "john.doe@example.com",
     "balance": 100.50
   }
```
