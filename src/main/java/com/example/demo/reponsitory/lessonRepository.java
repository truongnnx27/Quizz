package com.example.demo.reponsitory;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface lessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findByLessonType(LessonType lessonType);
}
