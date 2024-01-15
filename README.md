


# Introduction 
Project is meant to calculate EMI

# Build and test

1. Open two tabs in terminal;
2. In one of tabs go to the folder "emi-calculator-FE" and type in command line "npm install" and then "ng serve" (if you dont have angular, you need to download it);
3. In another tab go to the folder "emi-calculator-backend" perform "mvn spring-boot:run";
4. go to http://localhost:4200/ and fill in your data;
5. check the tab with mvn spring-boot:run, verify if data are inserted;
6. go to http://localhost:8080/h2-console/login.jsp type in "admin" password, check the inserted data in EMI table;
7. run mvn clean install to verify if all tests are passing;