package com.example.demo.controller;

import com.example.demo.MapStruct.EntityMapper;
import com.example.demo.dto.LessonDTO;
import com.example.demo.dto.LessonTypeDTO;
import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.LessonType;
import com.example.demo.entity.Quiz;
import com.example.demo.reponsitory.LessonTypeRepository;
import com.example.demo.service.LessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    private LessionService lessonService;

    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    //Created Lesson
    @PostMapping("/lessons")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO)
    {
        LessonDTO createdLesson = lessonService.createLesson(lessonDTO);
        return ResponseEntity.ok(createdLesson);
    }

    @GetMapping("/lessons")
    public  ResponseEntity<List<LessonDTO>> getAllLesson()
    {
        List<LessonDTO> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    //Created quizz
    @PostMapping("/lessons/{lessonId}/quiz")
    public  ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO, @PathVariable Long lessonId)
    {
        QuizDTO createdQuiz = lessonService.createQuiz(lessonId,quizDTO);
        return ResponseEntity.ok(createdQuiz);
    }

    //lấy danh sách Lesson theo LessonType
    @GetMapping("lessons/by-type/{typeName}")
    public ResponseEntity<List<LessonDTO>> getLessonByType(@PathVariable String typeName)
    {
        List<LessonDTO> lessons = lessonService.getLessonsByType(typeName);
        return ResponseEntity.ok(lessons);
    }


    @GetMapping("/lesson-types")
    public List<LessonTypeDTO> getAllLessonTypes() {
        List<LessonType> lessonTypes = lessonTypeRepository.findAll();
        return lessonTypes.stream()
                .map(EntityMapper.INSTANCE::lessonTypeToLessonTypeDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/lesson-types")
    public LessonTypeDTO createLessonType(@RequestBody LessonTypeDTO lessonTypeDTO) {
        LessonType lessonType = EntityMapper.INSTANCE.lessonTypeDTOToLessonType(lessonTypeDTO);
        lessonType = lessonTypeRepository.save(lessonType);
        return EntityMapper.INSTANCE.lessonTypeToLessonTypeDTO(lessonType);
    }

}

