# Wallet-API-Services
deposite and withdraw amount from a wallet

Your task is to create a *spring-boot* web application which can be used by a bank. Obviously, we don't want you to set up all the services provided by a bank. Still you should implement an API which can be used by an administrator of the bank to perform the following tasks:
 
1. Create an account for a customer by providing the customer's name and email and get back an unique account number.
2. Deposit money to a previously created account by using the account number obtained in the previous step.
3. Withdraw money from an account as long as the account has sufficient balance.
4. Enquire about the balance of an account as well as the name and email of the customer who owns the account.
 
Your endpoints should consume and produce either JSON or XML. Also the program should have a persistence mechanism so data about accounts created and balances are not lost between restarts. The choice of databases and persistence provider is yours, but we would prefer you use MySQL and Hibernate, through Spring Data JPA.
 
The format of the data that the endpoints accept and return and the error messages returned in case of an illegal operation, such as depositing to a non-existing account, withdrawing a sum greater than the balance of an account, etc., are up to you and we are happy as long as you document them and they are meaningful.
 
Whether you allow multiple accounts for the same name and email is also your decision but if you do not, then arrange for an appropriate response when trying to create an account with a name and email pair that already exists in the database.
