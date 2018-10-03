# Individual Programming Project #1

### Description
Implement a RESTful Web Service for your online store.  The product and user data is stored on your company's local servers, and you will provide customers the ability to sign up for accounts, search products and place orders.

### Technologies and Languages needed
1. MySQL - for storing and retrieving data in the to and from your model.
2. Java
   a. JDBC - to connect your Data Access Object layer classes to your MySQL database. (See Spring JDBC tutorial
   b. Jersey RESTful Web Services framework (https://jersey.github.io/) - standard and portable JAX-RS API 
3. Apache Tomcat - to run your RESTful Web Service
4. Postman (or similar) - to test your REST API implementation

### Assignment
1. GitHub - Clone shared, public repository and create personal, private repo for this project.
	


MySQL set up
   a. See <spring tutorial> for database and user set up instructions
spring.datasource.url=jdbc:mysql://localhost:3306/db_store
spring.datasource.username=springuser
spring.datasource.password=ThePassword

Submission


Example formats for spec
1.
Example URI: http://localhost:8080/<<yourAppName>>/store/items/jdoe

Request: GET /items/{username}
Request body: (empty)
Response body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username: "jdoe",
    "email": "jdoe@gmail.com"
}

2.
Postman Example URI: http://localhost:8080/<yourAppName>/store/customers?fname=Jane&lname=Doe&email=jdoe@gmail.com


Request: POST /customers
Request body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username: "jdoe",
    "email": "jdoe@gmail.com"
}
Response body: (empty)




REST API
list all items
  /items
list items by keyword
  /items/:keyword

list item by id
  /items/:id

create user 
update user
delete user


add item to shoping cart
list items in user's shopping cart
remove item from shopping cart
buy item (remove from shopping cart and product list)


list users who bought a specific product
list products bought by a user



* Tutorials
REST Best practices - https://code-maze.com/top-rest-api-best-practices/
