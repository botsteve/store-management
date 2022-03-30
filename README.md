# store-management
<h1>Project architecture and use cases</h1>
This is a multi-module maven project, that uses an inmemory H2 database to store the data. 
The database schema has 2 tables with a 1-M relationship between them.
The Users table is also used by the Spring security to authenticate using basic auth into the application.
The initial user is test with the password test, although is hashed inside the databse using bcrypt encoder.
The main modules of the application are:

- application: Used for aggregating multiple bussiness services from domain. It has only the domain module as its dependency.
- domain: Used for defining Interfaces that will be implemented by the infrastructure module, business entities and services. It
  doesnt have any dependency of other modules.
- infrastructure: Mainly used for defining the database objects and repositories that will be used.
- exposition: Exposing the REST API and the main starting point of the application, it also depends on the other modules and builds the fat .jar.

Each of these modules have tested associated with them. The application uses the default profile to run.
