package com.keep.notes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private final List<String> notes = new ArrayList<>();

    public List<String> getAllNotes() {
        return notes;
    };

    public String addNote(String note) {
        notes.add(note);
        return note;
    };

}
