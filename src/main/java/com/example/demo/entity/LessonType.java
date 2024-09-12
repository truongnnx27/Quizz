package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Lesson_Types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "lessonType", fetch = FetchType.LAZY)
    @JsonBackReference // Ngăn vòng lặp ở phía LessonType
    private List<Lesson> lessons;
}

