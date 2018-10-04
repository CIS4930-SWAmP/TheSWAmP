# Individual Programming Project #1

### Description
Implement a RESTful Web Service for your online store.  The product and user data is stored on your company's local servers, and you will provide customers the ability to sign up for accounts, search products and place orders.

### Technologies and Languages needed
1. MySQL - for storing and retrieving data in the to and from your model.
2. Java
   - JDBC - to connect your Data Access Object layer classes to your MySQL database. 
      - [Spring JDBC tutorial] ()
   - [Jersey] (https://jersey.github.io/) RESTful Web Services framework - standard and portable JAX-RS API 
3. Apache Tomcat - to run your RESTful Web Service
4. [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) (or similar) - to test your REST API implementation

### Assignment
1. GitHub - Clone shared, public repository and create personal, private repository for this project.
2. MySQL set up
   - See { spring tutorial } for database and user set up instructions
```
spring.datasource.url=jdbc:mysql://localhost:3306/db_store
spring.datasource.username=springuser
spring.datasource.password=ThePassword
```

3. Implement the following REST API
The core of this project revolves around your RESTful Web Service taking the given input and returning the expected content.  The implementation of your controller(s), data access layer, and model will vary from student to student.

- lookup user
  - Postman Example URI: http://localhost:8080/< yourAppName >/store/customers/jdoe
```
Request: GET /customers/{username}
Request body: (empty)
Response body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username": "jdoe",
    "email": "jdoe@gmail.com"
}
```
- create user 
  - Postman Example URI: http://localhost:8080/< yourAppName >/store/customers?fname=Jane&lname=Doe&username=jdoe&email=jdoe@gmail.com
```
Request: POST /customers
Request body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username": "jdoe",
    "email": "jdoe@gmail.com"
}
Response body: (empty)
```

#### Additional REST API function

- create user 
```
Request: POST /customers
Request body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username": "jdoe",
    "email": "jdoe@gmail.com"
}
Response body: (empty)
```

- update user
```
Request: PUT /customers
Request body:
{
    "fname": "Jane",
    "lname": "Doe",
    "username": "jdoe",
    "email": "jdoe@gmail.com"
}
Response body: (empty)
```

- delete user
```
Request: DELETE /customers/{username}
Request body: (empty)
Response body: (empty)
```

- list all items
```
Request: GET /items
Request body: (empty)
Response body:
[
{
       "itemId": 107,
        "name": "Rose Cottage Girls' Hunter Green  Jacket Dress",
        "msrp": 20.0,
        "salePrice": 11.6,
        "upc": "048238056017",
        "shortDescription": "Paired with its' trendy jacket or worn alone, this dress will go anywhere in style.",
        "brandName": "Generic",
        "size": "16",
        "color": "Red",
        "gender": "Male"
},
{
       "itemId": 109,
        "name": "Blue Sweater",
        "msrp": 20.0,
        "salePrice": 11.6,
        "upc": "048238056019",
        "shortDescription": "Trendy sweater.",
        "brandName": "Generic",
        "size": "08",
        "color": "Blue",
        "gender": "Female"
}
]
```

- list items by keyword
```
Request: GET /items/search/{keyword}
Request body: (empty)
Response body:
[
{
       "itemId": 107,
        "name": "Rose Cottage Girls' Hunter Green  Jacket Dress",
        "msrp": 20.0,
        "salePrice": 11.6,
        "upc": "048238056017",
        "shortDescription": "Paired with its' trendy jacket or worn alone, this dress will go anywhere in style.",
        "brandName": "Generic",
        "size": "16",
        "color": "Red",
        "gender": "Male"
},
{
       "itemId": 109,
        "name": "Blue Sweater",
        "msrp": 20.0,
        "salePrice": 11.6,
        "upc": "048238056019",
        "shortDescription": "Trendy sweater.",
        "brandName": "Generic",
        "size": "08",
        "color": "Blue",
        "gender": "Female"
}
]
```

- list item by id
```
Request: GET /items/{id}
Request body: (empty)
Response body:
{
       "itemId": 107,
        "name": "Rose Cottage Girls' Hunter Green  Jacket Dress",
        "msrp": 20.0,
        "salePrice": 11.6,
        "upc": "048238056017",
        "shortDescription": "Paired with its' trendy jacket or worn alone, this dress will go anywhere in style.",
        "brandName": "Generic",
        "size": "16",
        "color": "Red",
        "gender": "Male"
}
```

- add item to shoping cart (Hint: If no current cart exists for this user, create at cart first).
```
Request: POST /carts
Request body:
{
    "productId": 123,
    "username": "jdoe"
}
Response body: (empty)
```

- show user's shopping cart
```
Request: GET /carts
Request body:
{
    "username: "jdoe",
}
Response body:
[
{
   "cartId": 002,
   "items":[
     {
      "productId":123,
      "productName": "Blue sweater",
      "msrp": 20.99,
      "salePrice": 15.99
     },
     {
      "productId":125,
      "productName": "Red sweater",
      "msrp": 20.99,
      "salePrice": 15.99
     }
   ]
}
]
```

- remove item from shopping cart
```
Request: DELETE /carts
Request body:
{
   "cartId": 002,
   "productId":123
}
Response body: (empty)
```

- buy item (adjust shopping cart status and product list/count)
```
Request: PUT /carts/purchase/{cartId}
Request body: (empty)
Response body: (empty)
```


- list users who bought a specific product
```
Request: GET /carts
Request body:
{
  "productID":123
}
Response body: (empty)
```
- list products bought by a user
```
Request: GET /carts
Request body:
{ 
    "username: "jdoe"
}
Response body: (empty)
```

### Tutorials
- REST Best practices - https://code-maze.com/top-rest-api-best-practices/

### Submission

1. In GitHub, upload all code, and add the TAs as collaborators (CIS4930WepAppTA).
   - Do NOT make changes after the deadline. We will use the final timestamp as the submission time.
   - If you are not using Maven as your build tool, you must submit a comparable config file and clear, complete steps to build.
2. In Canvas, provide your GitHub username and a link to this project’s repository.

