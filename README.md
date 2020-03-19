<h2>Library Management System - Java + Springboot + JPA + H2 database + Spring Security + Junit 5 Mockito + Jacoco</h2>

A coding assignment by Synthesis, in which a system	 is designed to	create librarian and issue books to the student.
<br>A Java application using SpringBoot framework is created to perform following functionalities :
<ul>
<li>Save Book and List of Books</li>
<li>View All Books</li>
<li>Create Librarian, View All Librarians and Delete Librarian</li>
<li>Issue Books to Students</li>
<li>View All issued books</li>
<li>Return Books from User</li>
</ul>

<h3>Requirements</h3>
For building and running the application you would require:<br>

<ul>
<li>JDK 11</li>
<li>Java IDE</li>
<li>Maven</li>
<li>GIT</li>
<li>Rest Client (example POSTMAN)</li>
</ul>

<h3>Running the application</h3>
Run following command in to build and run the application <b> ./mvnw clean spring-boot:run</b>

<h3>Documentation</h3>
<b>Swagger</b> software is used to have a detailed view of all rest endpoints. It is configured within the springboot java application to automate the generation of rest endpoints details.
When application is up and running, hitting this URL would display the details, <b><i>http://localhost:8080/swagger-ui.html</b></i>



<h3>Technologies and Frameworks used</h3>
Following technologies, tools and frameworks are used to develop a system with end to end flow of collecting user's information, it's usage types, thus assuring a good code quality standards.<br>
<ul>
<li>Language: Java 11</li>
<li>Build and Packaging: Maven</li>
<li>Application Framework: Spring Boot</li>
<li>Persistence Framework: Spring JPA </li>
<li>Application Documentation: Swagger</li>
<li>Database: H2</li>
<li>Unit Testing: Junit 5, Mockito</li>
<li>Integration Testing: Spring Test</li>
<li>Coverage Tool: Jacoco</li>
<li>Security: Spring Boot Security</li>
<li>Rest Client: Postman</li>
</ul>

<h3>In memory database</h3>
H2 Database is used for storing and retrieving data saved and fetched from various endpoints.
When application is up, following URL <b><i>http://localhost:8080/h2-console</i></b> gives a view of database layout.<br>
Schema name : synthesis
Tables : User, Book, Issue


<h3>Test cases</h3>
<ul>
Application has following test cases
<li>Unit test cases</li>
<li>Controller test cases</li>
<li>Integration test cases</li>
</ul>

<h3>Running test cases and checking coverage</h3>
<ul>
<li>Run following command to run unit and integration test cases <b> ./mvnw clean test</b></li>
<li>Check runtime generated test coverage report in folder \\LibraryManagementSystem\synthesis\target\jacoco-ut\index.html. Also attached already run test report having 100% of test coverage in code structure as <b>index.html </b></li>
</ul>

<h3>Packaging</h3>
<ul>
<li><b>entity</b> — to hold our entities</li>
<li><b>repository</b> — to communicate with the database</li>
<li><b>service</b> — to hold our business logic</li>
<li><b>controller</b> — to listen to the client</li>
<li><b>config</b> — holds configuration used for swagger and spring security</li>
<li><b>constants</b> — contains constant for application wide usage</li>
<li><b>exception</b> — to hold custom designed exceptions</li>
</ul>

<h3>Spring security</h3>
<ul>
Application has following authorized roles for specific reponsibilities
<li>ADMIN - Add, Delete and View Librarian</li>
<li>LIBRARIAN - Add, View, Issue and Return books to students</li>
</ul>

