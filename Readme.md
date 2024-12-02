# Banking Transactions API

## Overview

API performing following operations

- Create a new user account with an initial balance.
- Transfer funds from one account to another.
- Retrieve the transaction history for a given account.

## Tech Stack

- **Framework**: Spring Boot

## Prerequisites

- JDK 17 (e.g., Zulu JDK 17)
- Maven
- Git

## Setup and Run

1. Clone the repository:
   ```bash
   git clone git@github.com:Ajaypal-Singh-Gill/bank-api.git
   cd bank-api
   ```
2. Build the project:

    ```bash
    mvn clean install
    ```
3. Run the application:

    ```bash
    mvn spring-boot:run
    ```
   
4. Access the service at http://localhost:8080.
5. Alternatively, access the deployed service directly at (Due to the free plan, instance will spin down with inactivity, which can delay requests by 50 seconds or more.):
      [https://bank-api-o074.onrender.com](https://bank-api-o074.onrender.com)
   
## API Endpoints

1. Service Up Status Check

```bash
# For local testing
curl --location 'http://localhost:8080/ping' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

# For testing on deployed server
curl --location 'https://bank-api-o074.onrender.com/ping' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'
```

2. Create New User Account

```bash
# For local testing
curl --location 'http://localhost:8080/create_new_user_account' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "john",
    "lastName": "Doe",
    "email": "john.doe@gmail.cm",
    "password": "password",
    "balance": 800
}'

# For testing on deployed server
curl --location 'https://bank-api-o074.onrender.com/create_new_user_account' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "john",
    "lastName": "Doe",
    "email": "john.doe@gmail.cm",
    "password": "password",
    "balance": 800
}'
```

3. Transfer Funds

```bash
# For local testing
curl -X POST http://localhost:8080/transfer_funds \
-H "Content-Type: application/json" \
-d '{
    "fromAccountId": 1,
    "toAccountId": 2,
    "amount": 100.00
}'

# For testing on deployed server
curl -X POST https://bank-api-o074.onrender.com/transfer_funds \
-H "Content-Type: application/json" \
-d '{
    "fromAccountId": 1,
    "toAccountId": 2,
    "amount": 100.00
}'
```

4. Get Transaction History

```bash
# For local testing
curl -X GET http://localhost:8080/1/transactions

# For testing on deployed server
curl -X GET https://bank-api-o074.onrender.com/1/transactions

```

## Assumptions

Create a New User Account

1. Non-negative balances only.
2. Email IDs must be unique across all user accounts.
3. Accounts can be created with a balance of zero, provided it is explicitly set.

Transfer Funds
1. Transactions (successful or failed due to insufficient funds) are recorded for both source and destination accounts.
2. There is no minimum or maximum limit on the transfer amount.

Retrieve Transaction History
1. The transaction history provides all recorded transactions for the given account, including successful and failed attempts.
2. Balance is not round off. It is stored and shown as is.

## Project Structure
- **Controller**: Handles HTTP endpoints (`AccountController`).
- **Service**: Business logic layer (`AccountService`).
- **Repository**: In-memory storage implementation (`InMemoryUserRepo`, etc.).
- **DTOs**: Data Transfer Objects for API communication (`CreateUserAccountRequest`, `CreateUserAccountResponse`, etc.).