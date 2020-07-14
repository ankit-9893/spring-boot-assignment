# Spring boot crud application using MySql database

Develop a spring boot a application using Mysql database with enabling spring boot http basic auth security

**Software required to run application**
 
*Java8*

*Maven*

*Any IDE- STS/ Eclipse/ Intellij*

*Mysql Database*

*Postman- to check rest points*

**Database configuration**

*username- root*

*password- root*


**Spring boot endpoints**

### For admin operations :- AdminController

    1. Admin can add any employee of type employee & manager [link](localhost:8080/api/admin/create)

    2. Delete any other employee by name & emlpoyee_id [link]

    3. Can change the password [link]

    4. Get all employees detalils by name & emlpoyee_id 

    5. Get all employee detalils based on their roles

### For manager operations : ManagerController

    1. Manager can add any employee of type employee [link](localhost:8080/api/manager/create)    

    2. Delete any other employee having role employee by name & emlpoyee_id [link]

    3. Can change the password [link]

    4. Get all employee having role employee detalils by name & emlpoyee_id 

## run application

Use Postman to test rest api

**For admin user**

Use this query after running application 

**insert into employee(empid, address, city, dob, first_name, last_name, mobile_no, password, roles,user_name) values(1, 'Indore', 'Mhow', '1999-12-12', 'first', 'last', '000000000', 'root', 'ROLE_ADMIN', 'root');**






