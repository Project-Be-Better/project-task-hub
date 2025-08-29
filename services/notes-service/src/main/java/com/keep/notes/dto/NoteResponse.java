package com.keep.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for returning note details in API responses.
 */
@Data
@AllArgsConstructor
public class NoteResponse {
    /**
     * The unique identifier of the note.
     */
    private Long id;

    /**
     * The title of the note.
     */
    private String title;

    /**
     * The content/body of the note.
     */
    private String content;
}
