# TODO LIST

The application receives data from the user through the web interface and writes the information to the database.
Implemented functionality for modifying existing records (you can change the content and status of the task), as well as deleting unnecessary records.

### Stack of technologies used in the project

* Java 17
* Spring Boot
* Spring Data
* Spring MVC 
* PostgreSQL
* Thymeleaf
* Docker
* Maven
* HTML
* CSS

### Run the project

- Clone the project to your computer.
- Start the database server locally (PostgreSQL). I recommend doing this through docker.

Related command:
```
   docker run --name TodoList -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
```
Connecting to DB
```
  url: jdbc:postgresql://localhost:5432/postgres
  username: postgres
  password: postgres
```
- Start the Spring Boot application `TodoListApplication`