# Notes Service

The Notes Service is a microservice within the Project Task Hub monorepo. It is responsible for creating, reading, updating, and deleting notes.

## Tech Stack

- Framework: Spring Boot 3.x
- Language: Java 17
- Build Tool: Maven
- Containerized: Docker (Multistage)
- Orchestrated With: Docker Compose

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven (or use the included Maven Wrapper)
- Docker and Docker Compose

### Running with Maven

```bash
cd services/notes-service
./mvnw spring-boot:run  # On Windows use .\mvnw spring-boot:run
```

The service will be available at: [http://localhost:8080](http://localhost:8080)

### Running with Docker Compose

```bash
cd path/to/project-task-hub
docker-compose -f infra/docker/docker-compose.yml up --build notes-service
```

## Building and Testing

### Building the JAR

```bash
cd services/notes-service
./mvnw clean package -DskipTests
```

The JAR file will be located in the `target/` directory.

### Running Tests

```bash
./mvnw test
```

## API Endpoints

### Health Check

- Method: GET
- URL: `/api/notes/hello`
- Description: Simple endpoint to verify that the service is running.
- Example Response:

```json
{
  "message": "Hello from Notes Service!"
}
```

## Project Folder: `services/notes-service/`

This folder contains all Spring Boot application source code and is containerized using a Dockerfile located under `infra/docker/notes-service/`.
