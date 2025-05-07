<div id="top"></div>

# <u>Fruit Entity Persistence with JPA, H2 and MVC</u>

## Table of Contents
- [Description](#Description)
- [Technologies Used](#Technologies-Used)
- [Requirements](#Requirements)
- [Installation and Execution](#installation-and-execution)
- [Deployment](#Deployment)
- [Contributions](#Contributions)


## Description

This project demonstrates how to persist a "Fruit" entity in an H2 in-memory database using the Java Persistence API (JPA) and the Model-View-Controller (MVC) architectural pattern.

### Entity Definition

The `Fruit` entity has the following properties:

* `int id`: A unique identifier for the fruit.
* `String nom`: The name of the fruit.
* `double quantitatQuilos`: The quantity of the fruit in kilograms.

### Project Structure

Following the MVC pattern, the project is organized into the following packages (assuming the main package is `cat.itacademy.s04.t02.n01`):

* `cat.itacademy.s04.t02.n01.controllers`: Contains the controller classes responsible for handling incoming HTTP requests and interacting with the service layer.
* `cat.itacademy.s04.t02.n01.model`: Contains the entity classes representing the data model (in this case, the `Fruit` entity).
* `cat.itacademy.s04.t02.n01.repository`: Contains the repository interfaces responsible for data access and persistence using JPA.
* `cat.itacademy.s04.t02.n01.exception`: (Optional) Contains any custom exception classes used in the application.

### API Endpoints

The `FruitaController` (located in the `controllers` package) provides the following REST API endpoints to manage fruit data:

* `POST http://localhost:8080/fruita/add`: Adds a new fruit. The request body will likely contain the `nom` and `quantitatQuilos` of the fruit.
* `PUT http://localhost:8080/fruita/update`: Updates an existing fruit. The request body will likely contain the `id`, `nom`, and `quantitatQuilos` of the fruit to be updated.
* `DELETE http://localhost:8080/fruita/delete/{id}`: Deletes a fruit with the specified `{id}`.
* `GET http://localhost:8080/fruita/getOne/{id}`: Retrieves the details of a fruit with the specified `{id}`.
* `GET http://localhost:8080/fruita/getAll`: Retrieves a list of all fruits.

This setup provides a basic RESTful API for managing `Fruit` entities persisted in an H2 database using JPA and following the principles of the MVC architecture.

## Technologies Used

* Java
* Maven
* Spring Boot DevTools
* Spring Web
* Spring Data JPA
* H2 Database
* Postman
* HTTP navigator

## Requirements

* Java 21 or higher.

## Installation and Execution

1. 	Install Java 21 or higher on your system.
2.  Download the project.
3.  Import it into IDE
4.  Compile the project using a Java compiler or an IDE.
5.  Run the main class of the project.

## Deployment

The compiled files need to be deployed in the designated location.

## Contributions

We welcome contributions to this project. Please follow these guidelines:

1.  Read this document thoroughly.
2.  Check the open "issues" to see if there are any tasks you can help with or report any bugs you find.
3.  Download a local copy or clone the project to work on it.

	* You can either create a "fork" of the repository or create a new branch to develop your code.
	* Once you are finished, if you want to contribute to the original project, you can submit a "pull request" to request merging your code with the main branch of the repository.

[Back to top](#top)