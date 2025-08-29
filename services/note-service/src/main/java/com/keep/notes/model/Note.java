package com.keep.notes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a note in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notes")
public class Note {

    /**
     * The unique identifier for the note.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the note. Cannot be null.
     */
    @Column(nullable = false)
    private String title;

    /**
     * The content/body of the note. Maximum length is 2000 characters.
     */
    @Column(length = 2000)
    private String content;
}
