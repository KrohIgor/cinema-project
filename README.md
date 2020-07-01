# cinema-project 

![Header Image](src/main/resources/cinema.jpg)

# Table of Contents

[Project purpose](#purpose)

[Project structure](#structure)

[For developer](#developer)

[Author](#author)

## <a name='purpose'></a>Project purpose

This project is a simplified API buying movie tickets online.

<hr>

This cinema performs these basic functions:

- Registration, log in forms and logout
- Shopping cart and order services
- Two roles: User and Admin

<hr>

This project has authentication, DAO and Service layers.

## <a name='structure'></a>Project structure

- Java 11
- Maven
- MavenCheckstylePlugin 3.1.1
- Spring version 5.2.6.RELEASE
- Hibernate version 5.4.15.Final
- javax.servlet
- mysql-connector-java 8.0.20
- log4j 1.2.17
- taglibs
- jackson

## <a name='developer'></a>For developer
To run this project you need to install:

- <a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html">Java 11</a>
- <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>
- <a href="https://www.mysql.com/downloads/">MySQL</a> (can use another RDBMS)

<hr>

Add this project to your IDE as Maven project.

Add Java SDK 11 in project structure.

Configure Tomcat:
- Add war
- Add Java SDK 11

Change a path to your Log file in **src/main/resources/log4j.properties** on line 12.

<hr>

To work with MySQL you need to:
- Use  create schema in MySQL DB
- Change username and password to match with MySQL in **src/main/resources/db.properties** on 4 and 5 lines

Run the project:

Main page is at URL: .../{context_path}

What to add a user with administrator rights need to send a post request ".../inject" (Email - "admin@ukr.net", Password - "4321")

What to register to send a post request ".../register"

After that you need to log in and you can use the controllers by sending the necessary requests.

## <a name='author'></a>Author
[Ihor Krokhmal](https://github.com/KrohIgor)
