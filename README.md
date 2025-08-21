## Starting the project

Follow the steps below to start the application with a database and Flyway migrations:

1. **Start docker-compose**
   ```bash
   docker-compose up -d

   
2. **Build the project**
    ```bash
    ./gradlew clean build

3. **Start the Spring Boot application**
   ```
   The endpoints are automatically generated from the defined OpenAPI specification file.
   Swagger UI is available after starting the application and allows you to view and test the API operations.


 **Task requirements**
```
Develop a simple application for basic management of bank accounts and related bank transfers.
Following user stories need to be implemented:
• As a user, you should be able to see a list of created accounts
• As a user, you can add new accounts. Each account must have unique Name and IBAN.
• As a user, you can edit or freeze/unfreeze existing account from the list
• As a user, you should be able to see a list of all transfers for certain account
• As a user, you can create a bank transfer between two accounts
Technical details:
1. Create a database with two tables - Accounts and Transfers.
Required columns for each table are:

o Bank accounts
• Id
• Name
• IBAN
• Status (Active, Frozen)
• AvailableAmount
• CreatedOn
• ModifiedOn

o Transfers
• Id
• AccountId
• BeneficiaryAccountId
• Type (Credit, Debit)
• Amount
• CreatedOn
• ModifiedOn

2. The API should exposes endpoints for

o Bank accounts
• List of accounts
• Account by Id
• Create account
• Update account
• Freeze account
• Unfreeze account

o Transfers
• List of transfers by account Id
• Get transfer by Id
• Create transfer between accounts

Front-end must be implemented in React or other JavaScript technology.
Back-end must be written in Java.
Database columns and endpoints list are not definitive - feel free to add columns or endpoints as you see fit.
Same applies to data validations as well.
