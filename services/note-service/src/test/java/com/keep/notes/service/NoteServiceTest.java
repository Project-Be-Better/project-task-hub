package com.keep.notes.service;

import com.keep.notes.dto.NoteRequest;
import com.keep.notes.dto.NoteResponse;
import com.keep.notes.model.Note;
import com.keep.notes.repository.NoteRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

        @Test
        void getAllNotesReturnsMappedResponses() {

            // 🏗️ ARRANGE : Create the 3 cards
            Note note1 = new Note();
            note1.setId(1L);
            note1.setTitle("First Title");
            note1.setContent("First Content");

            Note note2 = new Note();
            note2.setId(2L);
            note2.setTitle("Second Title");
            note2.setContent("Second Content");

            Note note3 = new Note();
            note3.setId(3L);
            note3.setTitle("Third Title");
            note3.setContent("Third Content");

            when(noteRepository.findAll()).thenReturn(List.of(note1, note2, note3));

            // 🎬 ACT : call the service methods
            List<NoteResponse> result = noteService.getAllNotes();

            // 🛫 ASSERT : Check the results are mapped correctly
            assertEquals(3, result.size());
            assertEquals("First Title", result.get(0).getTitle());

        }

    @Test
    void createNoteSavesAndReturnsResponse() {
        NoteRequest request = new NoteRequest();
        request.setTitle("New Note");
        request.setContent("New Content");

        Note savedNote = new Note();
        savedNote.setId(10L);
        savedNote.setTitle("New Note");
        savedNote.setContent("New Content");

        when(noteRepository.save(any(Note.class))).thenReturn(savedNote);

        NoteResponse response = noteService.createNote(request);

        assertEquals(10L, response.getId());
        assertEquals("New Note", response.getTitle());
        assertEquals("New Content", response.getContent());
    }

    @Test
    void getNoteByIdReturnsResponseIfExists() {
        Note note = new Note();
        note.setId(5L);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        when(noteRepository.findById(5L)).thenReturn(Optional.of(note));

        Optional<NoteResponse> response = noteService.getNoteById(5L);

        assertTrue(response.isPresent());
        assertEquals("Test Title", response.get().getTitle());
    }

    @Test
    void getNoteByIdReturnsEmptyIfNotFound() {
        when(noteRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<NoteResponse> response = noteService.getNoteById(99L);

        assertFalse(response.isPresent());
    }

    @Test
    void updateNoteUpdatesAndReturnsResponseIfExists() {
        Note existingNote = new Note();
        existingNote.setId(7L);
        existingNote.setTitle("Old Title");
        existingNote.setContent("Old Content");

        NoteRequest updateRequest = new NoteRequest();
        updateRequest.setTitle("Updated Title");
        updateRequest.setContent("Updated Content");

        Note savedNote = new Note();
        savedNote.setId(7L);
        savedNote.setTitle("Updated Title");
        savedNote.setContent("Updated Content");

        when(noteRepository.findById(7L)).thenReturn(Optional.of(existingNote));
        when(noteRepository.save(existingNote)).thenReturn(savedNote);

        Optional<NoteResponse> response = noteService.updateNote(7L, updateRequest);

        assertTrue(response.isPresent());
        assertEquals("Updated Title", response.get().getTitle());
        assertEquals("Updated Content", response.get().getContent());
    }

    @Test
    void updateNoteReturnsEmptyIfNotFound() {
        NoteRequest updateRequest = new NoteRequest();
        updateRequest.setTitle("Title");
        updateRequest.setContent("Content");

        when(noteRepository.findById(123L)).thenReturn(Optional.empty());

        Optional<NoteResponse> response = noteService.updateNote(123L, updateRequest);

        assertFalse(response.isPresent());
    }

    @Test
    void deleteNoteDeletesAndReturnsTrueIfExists() {
        when(noteRepository.existsById(8L)).thenReturn(true);

        boolean result = noteService.deleteNote(8L);

        verify(noteRepository).deleteById(8L);
        assertTrue(result);
    }

    @Test
    void deleteNoteReturnsFalseIfNotExists() {
        when(noteRepository.existsById(77L)).thenReturn(false);

        boolean result = noteService.deleteNote(77L);

        verify(noteRepository, never()).deleteById(anyLong());
        assertFalse(result);
    }
}
