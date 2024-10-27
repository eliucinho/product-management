
# Price Service API

## Overview

The **Price Service API** is a Spring Boot application designed to provide pricing information for a given product based on the date, product ID, and brand (chain). This API uses an in-memory H2 database to store pricing details and responds with the applicable price when queried.

The data model for prices includes fields like `brandId`, `productId`, `startDate`, `endDate`, `price`, and others. 

---
## Table of Contents

- [How to Download and Run the Application](#how-to-download-and-run-the-application)
- [How to Use the API](#how-to-use-the-api)
- [Architecture](#architecture)
    - [Component Diagram](#component-diagram)
    - [Class Diagrams](#class-diagrams)
- [Testing the API](#testing-the-api)
- [Diagrams](#diagrams)
    - [Core Components](#core-components-diagram)
    - [Main Components](#main-components-diagram)
    - [Sequence Diagram](#sequence-diagram)
---

## How to Download and Run the Application

### Prerequisites
- **Java 17+**
- **Maven 3+**

### Steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/eliucinho/product-management.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd product-management
   ```

3. **Build the Project:**
   Use Maven to download mandatory dependency:
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   You can run the application using the `mvn spring-boot:run` command.


5. **Access the API Documentation:**
   Once the application is running, open a browser and visit the Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## How to Use the API

You can query the price using the `/prices` endpoint. Here are some example requests using `curl`:

#### Example Request 1: Query at 10:00 on June 14, 2020
```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1' \
  -H 'accept: */*'
```

#### Example Request 2: Query at 16:00 on June 14, 2020
```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1' \
  -H 'accept: */*'
```

---

## Architecture

- **Spring Boot**: The main framework used for building the application.
- **H2 Database**: In-memory database used for quick setup and testing.
- **Spring Data JPA**: To interact with the database.
- **Swagger/OpenAPI**: API documentation and testing interface.
- **JUnit & Mockito**: Used for unit and integration testing.

---

### Component Diagram
The component diagram illustrates the overall architecture of the system, including the API layer, service layer, strategy layer, and data access layer.

![Component Diagram](/src/main/resources/diagrams/png/components_diagram.png)

---

### Class Diagrams
Here are the class diagrams that break down the structure and relationships between the main entities:

- **Core Components Diagram**: This diagram explains the core classes and interfaces in the system.
  ![Core Components Diagram](/src/main/resources/diagrams/png/core_components_diagram.png)

- **Main Components Diagram**: This diagram further details the main functional components in the application.
  ![Main Components Diagram](/src/main/resources/diagrams/png/main_components_diagram.png)

---

## Testing the API

The application includes unit tests that validate the correct functioning of the API using `JUnit` and `Mockito`. The following scenarios are tested:

- **Test 1**: Query at 10:00 on June 14, 2020, for product 35455 and brand 1 (ZARA).
- **Test 2**: Query at 16:00 on June 14, 2020, for product 35455 and brand 1 (ZARA).
- **Test 3**: Query at 21:00 on June 14, 2020, for product 35455 and brand 1 (ZARA).
- **Test 4**: Query at 10:00 on June 15, 2020, for product 35455 and brand 1 (ZARA).
- **Test 5**: Query at 21:00 on June 16, 2020, for product 35455 and brand 1 (ZARA).

To run the tests:
```bash
mvn test
```

---

## Diagrams

### Core Components Diagram
The Core Components Diagram explains the fundamental classes and their relationships within the application.
![Core Components Diagram](/src/main/resources/diagrams/png/core_components_diagram.png)

### Main Components Diagram
The Main Components Diagram provides a detailed view of the main functional components used in the application.
![Main Components Diagram](/src/main/resources/diagrams/png/main_components_diagram.png)

### Sequence Diagram
This Sequence Diagram shows the interaction flow between components for a typical request-response cycle.
![Sequence Diagram](/src/main/resources/diagrams/png/sequence_diagram.png)

