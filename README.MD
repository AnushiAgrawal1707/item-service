# mykart-user-service


## Steps to Setup

### 1. Clone the application
```bash
https://github.com/AnujaHaranePersistent/user-service.git
```

### 2. Create MYSQL database
```bash
Execute database schema from /DB-Schema/schema.sql
```

### 3. Change MYSQL username and password as per your installation

``` bash
open src/main/resources/application.yml
```

change datasource.username and datasource.password as per your MYSQL installation 

### 4. Build and run the app using maven

```bash
mvn package
java -jar target/mykart-user-service 0.0.1-SNAPSHOT.jar
```
Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at http://localhost:5050.

# Explore Rest APIs

The app defines following CRUD APIs.

For User:

```bash

POST /v1/users/register                -   to register user 

POST /v1/users/login                   -   to authenticate user and generate token

PUT /v1/users/update/{user_id}         -   to update user with given identifier

DELETE /v1/users/delete/{user_id}      -   to delete user with given identifier


```
For Cart:

```bash

POST /v1/users/{user_id}/cart                -   to add items to cart 

GET /v1/users/{user_id}/cart/{item_id}       -   to get specific item from cart

PUT /v1/users/{user_id}/cart/update/{item_id}         -   to update quantity of item with given identifier

DELETE /v1/users/{user_id}/cart/delete/{item_id}      -   to delete item from cart with given identifier


```
