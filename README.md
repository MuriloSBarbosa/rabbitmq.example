# RabbitMQ Example with Spring Boot 🐇

This repository contains an example implementation of a RabbitMQ producer using Java Spring Boot.

## Architecture

This project is divided into the following components:

- **Receiver**: Contains the logic to receive messages from RabbitMQ.
- **Sender**: Contains the logic to send messages to RabbitMQ.
- **BookDto**: Data Transfer Object (DTO) used to represent the movie data being sent.

All dependencies are managed by a `pom.xml` file.

## Some Technologies

| Language                                                                                                                                          | Description               |
|---------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|
| <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" width="80" />                     | main language             |
| <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" width="80" /> | framework used            |
| <img src="https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white" alt="RabbitMQ" width="80" />            | message broker            |
| <img src="https://img.shields.io/badge/Lombok-FF2D20?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok" width="80" />                  | reducing boilerplate code |
| <img src="https://img.shields.io/badge/JavaFaker-00A4CC?style=for-the-badge&logo=java&logoColor=white" alt="JavaFaker" width="80" />              | generating random data    |

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

Make sure RabbitMQ is running and accessible. You can use Docker to run RabbitMQ:

```bash
docker compose up
```

## Scheduled Task

The `Sender` class contains a scheduled task that sends a random movie to RabbitMQ every 5 seconds.
The movie data is generated using JavaFaker.