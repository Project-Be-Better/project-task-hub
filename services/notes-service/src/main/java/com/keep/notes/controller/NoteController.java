package com.keep.notes.controller;

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
    };

    @GetMapping
    public ResponseEntity<List<String>> getAllNotes(){
        return ResponseEntity.ok(noteService.getAllNotes());
    };

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody String note){
        return ResponseEntity.ok(noteService.addNote(note));
    };
}
