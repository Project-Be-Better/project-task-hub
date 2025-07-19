package com.keep.notes.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    private boolean pinned;
    private boolean archived;
    private boolean trashed;

    private String color;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
