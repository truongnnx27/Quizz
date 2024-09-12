package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Course_Levels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String levelName;

    @OneToMany(mappedBy = "courseLevel", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Course> courses;
}

