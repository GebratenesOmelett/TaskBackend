# Task Application 
Desktop Task app created with Angular, Spring Boot and MySql.

## Technologies
* Bootstrap 5
* Angular 
* Java
* Spring Boot
* MySql
* Docker

## How to start docker image 
1. docker pull mysql
2. docker run -p 3306:3306 --name mysqlcontainertask -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tasksToDo -d mysql
3. docker network create networkmysql
4. docker network connect networkmysql mysqlcontainertask
5. docker run -p 8080:8080 --name taskkcontainer --net networkmysql -e MYSQL_HOST=mysqlcontainertask -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=tasksToDo -e MYSQL_USER=root -e MYSQL_PASSWORD=root taskapi

6. ## Example of use
The entire file is located in main folder : Task-api.postman_collection.json
* Getting token for sending requests :
* By Registration
![image](https://github.com/GebratenesOmelett/TaskBackend/assets/78979897/b66b1fdd-05db-451d-88df-d92fa27139a5)
* By Login
![image](https://github.com/GebratenesOmelett/TaskBackend/assets/78979897/c4af6b73-2a8b-4774-865b-c53e7a914c78)

