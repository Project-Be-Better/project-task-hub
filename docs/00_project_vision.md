# 📘 Project Vision – Google Keep–Inspired Notes App

## 1. Purpose

This project is a scalable, microservices-based web application inspired by Google Keep. It serves as a hands-on learning platform to apply software architecture principles, modern DevOps practices, and multi-language microservice development in a structured and portfolio-worthy manner.

It also serves as a core academic artifact aligned with the learning outcomes of SWE5001 – Architecting Scalable Systems.


## 2. Goals

- ✅ Apply **microservice architecture** using Java, JavaScript (Node.js), and Python
- ✅ Follow **Test-Driven Development (TDD)** throughout the backend
- ✅ Build a fully automated **CI/CD pipeline** with GitHub Actions
- ✅ Integrate **SAST**, **SCA**, and **DAST** tools to ensure code security and compliance
- ✅ Use **Docker** and **Infrastructure-as-Code** (Terraform) to manage deployments
- ✅ Produce strong technical documentation and architecture artifacts suitable for university submission


## 3. Target Users and Usage Scenarios

The app is designed for individual users who want to manage personal notes and ideas with rich labeling and organizational features.

### Example Use Cases:
- A student uses the app to capture ideas across multiple subjects
- A developer pins todos and tags them by project
- A teacher archives reminders and sets up color-coded labels for tasks


## 4. MVP Features

The minimum viable product will support:
- Creating, editing, deleting, and viewing notes
- Pinning, archiving, and trashing notes
- Assigning color and labels to notes
- Basic full-text search across notes
- User authentication (e.g., Firebase or Auth0)


## 5. Long-Term Enhancements

The system will be built to support future enhancements, such as:
- Real-time sync and multi-user collaboration
- Offline mode and Progressive Web App (PWA)
- Semantic and fuzzy search
- Reminder and notification features
- Role-based access and shared notes
- Event-driven components using Kafka
- Redis for caching and performance


## 6. Architectural Constraints

- Monorepo structure for all services and frontend
- RESTful APIs following Richardson Maturity Model Level 2 or above
- Dockerized services, orchestrated using Docker Compose
- Infrastructure provisioned via Terraform
- CI/CD managed through GitHub Actions from Day 1

## 7. Academic Relevance

This project is designed to showcase practical mastery in:
- Software architecture (SWE5001)
- Microservices and distributed systems
- Secure coding and quality gates
- Deployment automation
- Technical writing and documentation

The final submission will include domain models, sequence diagrams, layered architecture, service boundaries, deployment diagrams, and DevOps artifacts to meet academic standards.


## 8. Success Criteria

- Full end-to-end workflow functioning (create → pin → archive → search)
- Each backend service is test-covered, containerized, and CI-enabled
- All services documented and deployable with one command
- Clear visual and textual documentation for all layers
