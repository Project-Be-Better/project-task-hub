package com.keep.notes.repository;

import com.keep.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Note entities.
 * Provides CRUD operations and query methods for Note persistence.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
