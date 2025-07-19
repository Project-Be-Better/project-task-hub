package com.keep.notes.controller;

import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.model.Note;
import com.keep.notes.service.NoteService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * REST controller for managing notes.
 * Provides endpoints to create, read, update, and delete notes.
 */
@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NoteService noteService;

    /**
     * Constructor for NotesController.
     * 
     * @param noteService the service handling note operations
     */
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Test endpoint to verify service is running.
     * 
     * @return a simple status message
     */
    @GetMapping("/test")
    public String hello() {
        return "Service is working";
    }

    /**
     * Another test endpoint for demonstration.
     * 
     * @return a greeting message from the notes service
     */
    @GetMapping("/hello")
    public String helloString() {
        return "Hello from the notes service with request mapping";
    }

    /**
     * Get all notes.
     * 
     * @return a list of all notes
     */
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    /**
     * Get a note by its ID.
     * 
     * @param id the ID of the note
     * @return the note if found, otherwise 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new note.
     * 
     * @param note the note to create
     * @return the created note
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.createNote(note);
        return ResponseEntity.ok(createdNote);
    }

    /**
     * Update an existing note by ID.
     * 
     * @param id   the ID of the note to update
     * @param note the updated note data
     * @return the updated note if found, otherwise 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(id, note);
        if (updatedNote != null) {
            return ResponseEntity.ok(updatedNote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a note by ID.
     * 
     * @param id the ID of the note to delete
     * @return 204 if deleted, otherwise 404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
