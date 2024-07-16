# Student Report Generator App

This project is a Spring Boot application for managing students, subjects, and scores.

It can be found in the application_development package.

## Prerequisites

- Java 11
- Maven

## Building the Application

To build the application, run:

```bash
mvn clean package
```

## Running with Docker

To build the Docker image, run:

```bash
docker build -t techie-test .
```

To run the Docker image, run:

```bash
docker run -p 8080:8080 techie-test
```

## Endpoints

* /students: Endpoint for managing students.
* /subjects: Endpoint for managing subjects.
* /scores: Endpoint for managing scores.
* /reports: Endpoint for generating reports.