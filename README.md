# social-media-rest
This is a restful social-media web application.

## Description
The restful resources were built with Spring (Dependency Management), Spring MVC (or Spring REST), and Spring Boot with multiple features - versioning, exception handling, documentation (Swagger), basic authentication (Spring Security), filtering and HATEOAS etc.  Postman can be used as REST Services Client to test creating/deleting/retrieving user or users, and to test creating and retrieving a certain user’s posts etc.

## URLs
* /swagger-ui.html
* /filtering
* /filtering-list
* /hello-world
* /hello-world-bean
* /hello-world-bean
* /hello-world-internationalized
* /hello-world/path-variable/{name}
* /person/header
* /person/param
* /person/produces
* /v1/person
* /v2/person
* /jpa/users retrieveAllUsers
* /jpa/users createUser
* /jpa/users/{id} retrieveUser
* /jpa/users/{id} deleteUser
* /jpa/users/{id}/posts retrieveAllPosts
* /jpa/users/{id}/posts createPost
* /users retrieveAllUsers
* /users createUser
* /users/{id} retrieveUser
* /users/{id} deleteUser
* /actuator

### Dependencies

* Spring Data JPA, Spring Web, actuator, spring security, Hal-brower, Swagger2, Hateoas, devTools, h2, javax validation api, Jackson-dataformat-xml, .

### Installing and Executing Program

* download and import project to eclipse, run as Java application, test the above URLs via Postman. 
* For Swagger2 documentation, go to localhost:8080/swagger-ui.html
