# Notes Service - Requirements & Logic

## Overview

The Notes Service provides RESTful APIs to manage notes within the Project Task Hub application. It supports creating, reading, updating, and deleting notes, and is designed for easy integration with other services.

---

## Functional Requirements

- **Create Note:** Add a new note with a title and content.
- **Read Notes:** Retrieve all notes or a specific note by ID.
- **Update Note:** Modify the title and content of an existing note.
- **Delete Note:** Remove a note by its ID.

---

## API Endpoints

| Method | Endpoint     | Description       |
| ------ | ------------ | ----------------- |
| GET    | `/notes`     | List all notes    |
| GET    | `/notes/:id` | Get a note by ID  |
| POST   | `/notes`     | Create a new note |
| PUT    | `/notes/:id` | Update a note     |
| DELETE | `/notes/:id` | Delete a note     |

---

## Service Logic

### getAllNotes

- Fetch all notes from the repository.
- Map each note entity to a `NoteResponse` DTO.
- Return the list of responses.

### createNote

- Create a new `Note` entity from the request DTO.
- Save the entity using the repository.
- Return a `NoteResponse` DTO with the saved entity's data.

### getNoteById

- Find a note by ID using the repository.
- If found, map to `NoteResponse` and return as `Optional`.
- If not found, return `Optional.empty()`.

### updateNote

- Find the note by ID.
- If found, update its fields from the request DTO and save.
- Return the updated note as `NoteResponse` in an `Optional`.
- If not found, return `Optional.empty()`.

### deleteNote

- Check if a note exists by ID.
- If it exists, delete it and return `true`.
- If not, return `false`.

---

## Data Models

- **Note:** Entity representing a note (fields: id, title, content).
- **NoteRequest:** DTO for incoming create/update requests.
- **NoteResponse:** DTO for outgoing responses.

---

## Suggestions for Improvement

- Add input validation and error handling.
- Implement authentication and authorization.
- Add automated tests for all service methods.
- Integrate API documentation (e.g., Swagger).
- Add logging, monitoring, and rate limiting.
- Provide Docker support and CI/CD pipeline.

---

## Production Readiness

Current rating: **2/5**  
Key production features (testing, validation, security, etc.) are not yet implemented.

---
