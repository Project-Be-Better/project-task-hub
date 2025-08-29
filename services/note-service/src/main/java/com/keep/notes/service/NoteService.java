package com.keep.notes.service;

import com.keep.notes.dto.NoteRequest;
import com.keep.notes.dto.NoteResponse;
import com.keep.notes.model.Note;
import com.keep.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // ✅ DONE
    public List<NoteResponse> getAllNotes() {
        // 1. Find All Notes from repository
        // 2. Stream the list of notes
        // 3. For Each note, build a NoteResponse to be sent to the frontend
        // 4. Collect all response objects into a list
        // 5. Return a list of NoteResponse objects
        return noteRepository.findAll()
                .stream()
                .map(note -> new NoteResponse(note.getId(), note.getTitle(), note.getContent()))
                .collect(Collectors.toList());
    }

    // ✅ DONE
    public NoteResponse createNote(NoteRequest noteRequest) {
        // 1. Create a new Note Entity Object (Starting empty)
        // 2. Copy data from RequestDTO to the Note entity
        // 3. Save it using Repository(DAO)
        // 4. Generate a DTO with the saved entity
        // 5. Return Response
        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        Note savedNote = noteRepository.save(note);
        return new NoteResponse(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());

    }

    // ✅ DONE
    public Optional<NoteResponse> getNoteById(Long id) {
        // 1. Call the repository to find an entity with id
        // 2. If present, map the Note Entity into NoteResponse DTO
        // 3. If not present, return Optional.empty()
        return noteRepository.findById(id)
                .map(foundNote -> new NoteResponse(foundNote.getId(), foundNote.getTitle(), foundNote.getContent()));
    }

    // ✅ DONE
    public Optional<NoteResponse> updateNote(Long id, NoteRequest updatedNote) {
        // 1. Lookup the Note Entity using the NoteRepository
        // 1.1. If Exist, proceed
        // 1.2. If does not, return Optional.empty()
        // 2. Update the fields of the existing Note Entity
        // 3. Save the Updated Entity back into the database
        // 4. Create a NoteResponse DTO from the entity and return
        return noteRepository.findById(id).map(foundNote -> {
            foundNote.setTitle(updatedNote.getTitle());
            foundNote.setContent(updatedNote.getContent());
            Note savedNote = noteRepository.save(foundNote);
            return new NoteResponse(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());
        });

    }

    // ✅ DONE
    public boolean deleteNote(Long id) {
        // 1. Lookup the Note Entity using the NoteRepository
        // 1.1. If Exist, proceed with deletion and return true
        // 1.2. If does not, return false
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }

        return false;
    };

}
