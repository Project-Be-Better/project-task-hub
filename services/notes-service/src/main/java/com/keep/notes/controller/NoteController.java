package com.keep.notes.controller;

import com.keep.notes.dto.NoteRequest;
import com.keep.notes.dto.NoteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.keep.notes.service.NoteService;

import java.util.List;

/**
 * REST controller for managing notes.
 * Provides endpoints for CRUD operations on notes.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    /**
     * Constructs a new NoteController with the given NoteService.
     *
     * @param noteService the service to handle note operations
     */
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Simple health check endpoint.
     *
     * @return a greeting string
     */
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello from the controller");
    }

    /**
     * Retrieves all notes.
     *
     * @return a list of all notes wrapped in a ResponseEntity
     */
    // ✅ DONE
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        // 1. Delegate the request to NoteService.getAllNotes
        // 2. Wrap the returned List<NoteResponse> with status 200 ok
        // 3. Return Response entity back to the client
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    /**
     * Retrieves a note by its ID.
     *
     * @param id the ID of the note to retrieve
     * @return the note if found, or 404 if not found
     */
    // ✅ DONE
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        // 1. Extract the id from the URL using path variable
        // 2. Delegate the NoteService.getNoteById to handle the lookup
        // 3. If the service rertuns a NoteResponse, wrap it with OK response
        // ResponseEntity.OK
        // 4. If the Service returns Optional.empty, respond with
        // ResponseEntity.notFound
        return noteService.getNoteById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    /**
     * Creates a new note.
     *
     * @param noteRequest the note data to create
     * @return the created note with HTTP status 201
     */
    // ✅ DONE
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest) {
        // 1. Accept request body as a NoteRequest DTO from JSON input
        // 2. Delegate to NoteService.createNote() to handle persistence
        // 3. Wrap the NoteResponse DTO inside ResponseEntity
        // 4. Set HTTP Status to 201 created (Best Practice for POST)
        // 5. Return ResponseEntity to the Client
        NoteResponse savedNote = noteService.createNote(noteRequest);
        return ResponseEntity.status(201).body(savedNote);
    }

    /**
     * Updates an existing note by its ID.
     *
     * @param id          the ID of the note to update
     * @param noteRequest the updated note data
     * @return the updated note if found, or 404 if not found
     */
    // ✅ DONE
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        // 1. Extract the Path Variable and RequestBody
        // 2. Delegate the service layer to attempt to update the note
        // 3. If update successful, return a ResponseEntity.ok() with NoteResponse DTO
        // 4. If not, return a 404
        return noteService.updateNote(id, noteRequest).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    /**
     * Deletes a note by its ID.
     *
     * @param id the ID of the note to delete
     * @return 204 if deleted, or 404 if not found
     */
    // ✅ DONE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        // 1. Extract id from the PathVariable
        // 2. Delegate to NoteService.deleteNote to perform deletion operation
        // 3. Return 204 for successful delete
        boolean isDeleted = noteService.deleteNote(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
