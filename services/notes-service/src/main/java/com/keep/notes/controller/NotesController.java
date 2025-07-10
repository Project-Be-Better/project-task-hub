package com.keep.notes.controller;

import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.model.Note;
import com.keep.notes.storage.InMemoryNoteStore;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final InMemoryNoteStore store;

    public NotesController(InMemoryNoteStore store) {
        this.store = store;
    }

    @GetMapping("/test")
    public String hello() {
        return "Service is working";
    }

    @GetMapping("/hello")
    public String helloString() {
        return "Hello from the notes service with request mapping";
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return store.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteByIdEntity(@PathVariable Long id) {
        return store.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
