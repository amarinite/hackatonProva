# Backend Hackaton Saló de l'Ocupació 

This API documentation walks you through a program which allows users to manage activities and users, and sign up users for said activities. The API is built using Spring Boot and is documented using Swagger. This was made as a test for the Saló de l'Ocupació Hackaton in Barcelona.

## Table of Contents
- [Getting Started](#getting-started)
- [Base URL](#base-url)
- [API Endpoints](#api-endpoints)
    - [User Endpoints](#user-endpoints)
    - [Activity Endpoints](#activity-endpoints)
- [Import/Export Activities](#importexport-activities)
- [Error Handling](#error-handling)
- [Swagger UI](#swagger-ui)

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- MongoDB

### Installation
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd hackaton-prova

2. **Install dependencies**:
    ```bash
    mvn clean install
3. **Configure the application.properties for MongoDB connection**:
    ```bash
    spring.data.mongodb.uri=<database>
4. **Run the application**:
    ```bash
    mvn spring-boot:run

5. **Access the API documentation via Swagger UI at http://localhost:8080/swagger-ui.html.**

## Base URL
The base URL for the API is:
http://localhost:8080/api

## API Endpoints

### User Endpoints

#### 1. Create User
- **Endpoint**: `/users`
- **Method**: `POST`
- **Request Body**:
```json
{
"name": "string",
"lastName": "string",
"email": "string",
"age": "integer"
}
```
- **Response**:
    - **201 Created**: Returns the created user object.

#### 2. Get All Users
- **Endpoint**: `/users`
- **Method**: `GET`
- **Response**:
    - **200 OK**: Returns a list of users.
    - **204 No Content**: If there are no users.

#### 3. Get One User
- **Endpoint**: `/users/{id}`
- **Method**: `GET`
- **Response**:
    - **200 OK**: Returns the selected user.
    - **404 Not Found**: If the user doesn't exist.

#### 4. Modify User
- **Endpoint**: `/users/{id}`
- **Method**: `PUT`
- **Request Body**:
```json
{
"name": "string",
"lastName": "string",
"email": "string",
"age": "integer"
}
```
- **Response**:
    - **201 Created**: Returns the modified user object.
    - **404 Not Found**: If the user doesn't exist.
#### 5. Delete User
- **Endpoint**: `/users/{id}`
- **Method**: `DELETE`
- **Response**:
    - **200 OK**: Deletes the selected user.
    - **404 Not Found**: If the user doesn't exist.

### Activity Endpoints

#### 1. Create Activity
- **Endpoint**: `/activities`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "name": "string",
      "description": "string",
      "maxCapacity": "integer",
      "enrolledUsers": ["userId1", "userId2"]
  }
- **Response**:
    - **201 Created**: Returns the created activity object.

#### 2. Get All Activities
- **Endpoint**: `/activities`
- **Method**: `GET`
- **Response**:
    - **200 OK**: Returns a list of activities.
    - **204 No Content**: If there are no activities.

#### 3. Assign User to Activity
- **Endpoint**: `/activities/{activityId}/users`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "userId": "string"
  }
- **Response**:
    - **200 OK**: Returns a list of activities.
    - **400 Bad Request**: If the maximum capacity has been reached.

#### 4. Import Activities
- **Endpoint**: `/activities/import`
- **Method**: `POST`
- **Request**: Multipart file upload
- **Response**:
    - **200 OK**: Message indicating successful import.
    - **400 Bad Request**: If the file format is invalid or cannot be processed.

#### 5. Export Activities
- **Endpoint**: `/activities/export`
- **Method**: `GET`
- **Response**:
    - **200 OK**: Returns a list of activities in JSON format.


## Import/Export Activities
- **Import**: Activities can be imported by sending a JSON file with an array of activity objects to the `/activities/import` endpoint.
- **Export**: Activities can be exported by sending a GET request to the `/activities/export` endpoint, which returns all activities in JSON format.

## Error Handling
All errors are handled globally using a `GlobalExceptionHandler`. Common errors include:
- **400 Bad Request**: When the request parameters or body are invalid.
- **404 Not Found**: When a user or activity does not exist.
- **500 Internal Server Error**: For unexpected server errors.

## Swagger UI
Swagger UI is integrated into the application, providing an interactive documentation interface. You can access the Swagger UI at the following URL: http://localhost:8080/swagger-ui.html

This interface allows you to test API endpoints directly from your browser and view detailed documentation for each endpoint.


