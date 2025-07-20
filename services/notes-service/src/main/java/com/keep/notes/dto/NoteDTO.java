package com.keep.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the Note
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String content;
}
