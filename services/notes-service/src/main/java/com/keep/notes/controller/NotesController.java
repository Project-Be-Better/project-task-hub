package com.keep.notes.controller;

import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.dto.NoteDTO;
import com.keep.notes.model.Note;
import com.keep.notes.service.NoteService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        try {
            List<NoteDTO> noteDTOs = noteService.getAllNotes()
                    .stream()
                    .map(this::toNoteDto)
                    .collect(Collectors.toList());

            if (noteDTOs.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(noteDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get a note by its ID.
     * 
     * @param id the ID of the note
     * @return the note if found, otherwise 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) {

        try {
            Optional<Note> noteOptional = noteService.getNoteById(id);
            return noteOptional
                    .map(note -> ResponseEntity.ok(toNoteDto(note))).orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Create a new note.
     * 
     * @param noteDTO the note to create
     * @return the created note
     */
    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        try {
            Note createdNote = noteService.createNote(toNoteEntity(noteDTO));
            return ResponseEntity.ok(toNoteDto(createdNote));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Update an existing note by ID.
     * 
     * @param id      the ID of the note to update
     * @param noteDTO the updated note data
     * @return the updated note if found, otherwise 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable Long id, @Valid @RequestBody NoteDTO noteDTO) {

        try {
            Note updatedNote = noteService.updateNote(id, toNoteEntity(noteDTO));

            if (updatedNote != null) {
                return ResponseEntity.ok(toNoteDto(updatedNote));
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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
        try {
            boolean deleted = noteService.deleteNote(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Converts a {@link Note} entity to a {@link NoteDTO} object.
     *
     * @param note the {@link Note} entity to convert
     * @return a {@link NoteDTO} containing the id, title, and content from the
     *         given note
     */
    private NoteDTO toNoteDto(Note note) {
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent());
    }

    /**
     * Converts a {@link NoteDTO} object to a {@link Note} entity.
     *
     * @param dto the NoteDTO object containing note data
     * @return a Note entity populated with data from the given NoteDTO
     */
    private Note toNoteEntity(NoteDTO dto) {
        Note note = new Note();

        note.setId(dto.getId());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        return note;
    }

}
