package com.keep.notes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keep.notes.model.Note;
import com.keep.notes.repository.NoteRepository;

/**
 * Service layer foir handling business logic related to notes
 * Intermediate layer between controller and repository
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Retrieves all the notes from the database
     * 
     * @return List of All Notes
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    };

    /**
     * Retrieves a single note by its id
     * 
     * @param id of the notes
     * @return an Optional containing the note if found, or empty otherwise
     */
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    };

    /**
     * Saves a new note to the database
     * 
     * @param note the notes to be created
     * @return the saved note with a generated id
     */
    public Note createNote(Note note) {
        return noteRepository.save(note);
    };

    /**
     * Updates the existing note by ID
     * If the note exists, its fields are updated and saved
     * 
     * @param id          ID of the note to update
     * @param updatedNote the new data to apply to the existing note
     * @return updated note if found or null if the note does not exist
     */
    public Note updateNote(Long id, Note updatedNote) {
        return noteRepository.findById(id).map(note -> {

            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setPinned(updatedNote.isPinned());
            note.setArchived(updatedNote.isArchived());
            note.setTrashed(updatedNote.isTrashed());
            note.setColor(updatedNote.getColor());

            return noteRepository.save(note);
        }).orElse(null);
    };

    /**
     * Deletes a note by ID
     * 
     * @param id the ID of the note to delete
     * @return true if the note was found and deleted , false other wise
     */
    public boolean deleteNote(Long id) {
        return noteRepository.findById(id).map(
                note -> {
                    noteRepository.delete(note);
                    return true;
                }).orElse(false);
    };
}
