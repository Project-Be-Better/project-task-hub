package com.keep.notes.controller;

import com.keep.notes.model.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.keep.notes.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello from the controller");
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note savedNote = noteService.createNote(note);
        return ResponseEntity.status(201).body(savedNote);
    }

}
