package com.keep.notes.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.keep.notes.model.Note;

@Component
public class InMemoryNoteStore {

    private final Map<Long, Note> store = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public InMemoryNoteStore() {
        createNote(new Note(null, "Sample Note 1", "This is a placeholder note."));
        createNote(new Note(null, "Sample Note 2", "Another test note."));
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(store.values());
    }

    public Optional<Note> getNoteById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Note createNote(Note note) {
        Long id = idCounter.incrementAndGet();

        note.setId(id);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());
        store.put(id, note);

        return note;
    }

}
