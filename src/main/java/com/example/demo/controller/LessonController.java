package com.example.demo.controller;

import com.example.demo.dto.LessonDTO;
import com.example.demo.dto.LessonTypeDTO;
import com.example.demo.entity.LessonType;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons/api")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/createLesson")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO)
    {
        LessonDTO createLesson = lessonService.createLesson(lessonDTO);
        return ResponseEntity.ok(createLesson);
    }

    @PutMapping("/updateLesson/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO)
    {
        LessonDTO updateLesson = lessonService.updateLesson(lessonDTO,id);
        return ResponseEntity.ok(updateLesson);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id)
    {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllLessonType")
    public ResponseEntity<List<LessonTypeDTO>> getAllLessonType()
    {
        List<LessonTypeDTO> getAllLessonTypes =lessonService.getAllLessonType();
        return ResponseEntity.ok(getAllLessonTypes);
    }
}
