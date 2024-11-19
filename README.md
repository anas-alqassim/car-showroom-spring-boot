
# Car Showroom Management API

## Project Description
This is a REST API built using the latest stable versions of **Java** and **Spring Boot** and **Postgres**. It provides functionality to manage car showrooms and their associated cars. The API follows modern design practices, including input validation, exception handling, and pagination, and integrates **Swagger UI** for documentation and testing.

---

## Features

### Car Showroom APIs
1. **Create Car Showroom**
   - **Inputs**:
     - `name`: Required, string, max 100 characters
     - `commercial_registration_number`: Required, 10 digits, must be unique
     - `manager_name`: Optional, string, max 100 characters
     - `contact_number`: Required, numeric, max 15 digits
     - `address`: Optional, string, max 255 characters
   - **Validations**: All fields must adhere to their constraints before saving.

2. **List Car Showrooms**
   - **Parameters**: Pagination and sorting enabled
   - **Response**: Lists all car showrooms with the following fields:
     - `name`
     - `commercial_registration_number`
     - `contact_number`

3. **Get Specific Car Showroom**
   - **Response**: Retrieve all details:
     - `name`
     - `commercial_registration_number`
     - `manager_name`
     - `contact_number`
     - `address`

4. **Update Car Showroom**
   - **Updatable Fields**: `contact_number`, `manager_name`, `address`
   - **Validation**: Only provided fields will be updated.

5. **Delete Car Showroom**
   - **Action**: Performs a soft delete. The showroom will no longer appear in the previous APIs.

6. **Create New Car in Car Showroom**
   - **Inputs**:
     - `vin`: Required, string, max 25 characters
     - `maker`: Required, string, max 25 characters
     - `model`: Required, string, max 25 characters
     - `model_year`: Required, numeric, max 4 digits
     - `price`: Required, decimal value
     - Car must be associated with a car showroom.
   - **Validations**: All fields must meet their respective criteria.

7. **List Cars with Showroom Details**
   - **Parameters**: Pagination enabled, with dynamic filtering on any result field (e.g., filter by `maker` or `maker` and `car_showroom_name`).
   - **Response**: Lists cars with the following fields:
     - `vin`
     - `maker`
     - `model`
     - `model_year`
     - `price`
     - `car_showroom_name`
     - `contact_number`

---

## Additional Features
- **Exception Handling**: Comprehensive error handling for invalid requests and server issues.
- **Database Version Control**: Utilized **Flyway** for managing database migrations.
- **Swagger UI**: Access the API documentation and testing interface at:
  - [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Authentication & Authorization**: Integrated with **Keycloak** for role-based access control.

---

## Test Accounts (Keycloak)
Two accounts are provided for testing purposes:

1. **Admin Account**:
   - Email: `admin@test.com`
   - Password: `admin`
   - Permissions: Full access (Create, Update, Delete, Retrieve)

2. **Non-Admin Account**:
   - Email: `test@test.com`
   - Password: `test`
   - Permissions: Retrieve only

---


---
