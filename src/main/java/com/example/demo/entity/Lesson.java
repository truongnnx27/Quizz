package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;
    private int duration;
    private int numberOfAttachments = 0;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "lesson_type_id")
    @JsonBackReference // Ngăn vòng lặp ở phía Lesson
    private LessonType lessonType;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    @JsonManagedReference // Ngăn vòng lặp ở phía Comment, Quiz, Session
    private List<Comment> comments;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Session> sessions;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}




