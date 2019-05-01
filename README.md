# Soldiers
Web application for managing a military system (soldiers, users, teams, missions, equipment, news) which contains three roles: ADMINISTRATOR, COMMANDER, SOLDIER.
## How it works
1) HTTP request is send from client side using AJAX (GET, POST, PUT, DELETE)
2) Rest controller on server side process the request and send response as JSON
3) Response message depends on result (e.g. a validation error)
4) If everything went well - table is updated without any refresh
## Used technologies
- Spring Boot
- Spring Data
- Spring MVC
- Spring REST
- Spring Security
- Thymeleaf
- MySQL
- Maven
- jQuery
- Bootstrap
- SweetAlert - https://sweetalert.js.org/
- Sorttable - https://www.kryogenix.org/code/browser/sorttable/
## How to run
1) Set up MySQL
```
mysql -u root -p 
> CREATE DATABASE soldiers;
> CREATE USER 'soldier_user'@'localhost' IDENTIFIED BY 'zaq1@WSX';
> GRANT ALL PRIVILEGES ON soldiers.* TO 'solidier_user'@'localhost';
> FLUSH PRIVILEGES;
> source /your/path/src/main/resources/script.sql;
```
2) Run project in terminal
```
mvn spring-boot:run
```
3) Go to 
```
localhost:8080/
```
4) Default login and password for administrator account:\
login: admin\
password: zaq1@WSX
## Database schema
![ERD](src/main/resources/erd.png?raw=true "ERD")
## Not logged user
Not logged user (soldier) can only proceed through registration process but only if he is already in database. He cannot just come and register for military system - administrator has to add him before.
## Any user has privileges to:
- check news
- change user's setting
## Administrator has privileges to:
- create, update and delete soldier
- change user's role and disable account if needed
- create, update and delete address
- create, delete and end mission
- create, update and delete team
- create and update or delete any news
## Commander has privileges to:
- check and update personal data
- check team
- create and update or delete own news
- manage missions
- manage equipment
## Soldier has privileges to: (*)
- check and update personal data
- check team
- check missions
- manage equipment
##
(*) - not done yet
