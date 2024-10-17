Account Service
Description
Account Service is a RESTful API that manages user accounts. This project allows users to create, retrieve, update, and delete accounts, with comprehensive error handling. It's built with Spring Boot, JPA, and includes unit tests using JUnit and MockMvc.

Features
Create Account: Users can create new accounts.
Retrieve Account: Fetch details of existing accounts by ID.
Update Account: Update account details.
Delete Account: Remove an account from the system.
Error Handling: Custom error messages for account/user not found.
Technologies
Java 17
Spring Boot
Spring Data JPA
H2 Database (for testing)
JUnit 5
MockMvc
Gradle
Requirements
JDK 17+
Gradle 7+
Docker (optional for running in containers)
Setup & Installation
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/account-service.git
cd account-service
Build the project:

bash
Copy code
./gradlew clean build
Run the application:

bash
Copy code
./gradlew bootRun
Run tests:

bash
Copy code
./gradlew test
API Endpoints
1. Create Account
POST /accounts

json
Copy code
Request Body:
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword"
}

Response:
HTTP 201 Created
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "createdAt": "2024-10-18T10:20:30Z"
}
2. Get Account by ID
GET /accounts/{id}

json
Copy code
Response:
HTTP 200 OK
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "createdAt": "2024-10-18T10:20:30Z"
}
3. Update Account
PUT /accounts/{id}

json
Copy code
Request Body:
{
  "name": "John Smith",
  "email": "johnsmith@example.com"
}

Response:
HTTP 200 OK
{
  "id": 1,
  "name": "John Smith",
  "email": "johnsmith@example.com",
  "updatedAt": "2024-10-18T12:34:56Z"
}
4. Delete Account
DELETE /accounts/{id}

json
Copy code
Response:
HTTP 204 No Content
Error Handling
The API provides meaningful error responses in case of failure. Here are some common errors:

Status Code	Error Code	Message
404	ACC0004	Account not found
400	ACC0002	Invalid request
500	ACC0005	Internal server error
