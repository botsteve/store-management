# store-management
<h1>Project architecture and use cases</h1>
This is a multi-module maven project, that uses an in-memory H2 database to store the data. 
The database schema has 2 tables with a 1-M relationship between them.
The Users table is also used by the Spring security to authenticate using basic auth and jdbc into the application.
The initial user has the username: test with the password test, although the password is hashed inside the databse using bcrypt encoder.
The main modules of the application are:

- application: Used for aggregating multiple bussiness services from domain. It has only the domain module as its dependency.
- domain: Used for defining Interfaces that will be implemented by the infrastructure module, business entities and services. It
  doesnt have any dependency of other modules.
- infrastructure: Mainly used for defining the database objects and repositories that will be used.
- exposition: Exposing the REST API and the main starting point of the application, it also depends on the other modules and builds the fat .jar.

Each of these modules have tested associated with them. The application uses the default profile to run.
In order to test the application i added the swagger dependency which provides an interface for testing the rest api. It is avaialble on
http://localhost:8080/swagger-ui.html
The logging, security configuration and exception handling is done through different classes in exposition module, config package.
In order to create a new product we must give a valid username. Bellow is an example of a 200 OK request.
![image](https://user-images.githubusercontent.com/32300503/160907472-68904e40-9bfc-40af-97cc-2a50fab2eb6c.png)
