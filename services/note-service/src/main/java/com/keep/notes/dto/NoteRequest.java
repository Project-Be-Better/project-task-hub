package com.keep.notes.dto;

import lombok.Data;

/**
 * Data Transfer Object for creating or updating a note.
 */
@Data
public class NoteRequest {
    /**
     * The title of the note.
     */
    private String title;

    /**
     * The content/body of the note.
     */
    private String content;
}
