package com.example.demo.service;


import com.example.demo.MapStruct.EntityMapper;
import com.example.demo.dto.LessonDTO;
import com.example.demo.dto.LessonTypeDTO;
import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.*;
import com.example.demo.reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessionService {
    @Autowired
    private lessonRepository lessonRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private LessonTypeRepository lessonTypeRepository;


    public LessonDTO createLesson(LessonDTO lessonDTO)
    {
        //kiểm tra lessonType
        LessonType lessonType= lessonTypeRepository.findByTypeName(lessonDTO.getLessonType().getTypeName());
        if (lessonType ==null)
        {
            //tạo mới
            lessonType = EntityMapper.INSTANCE.lessonTypeDTOToLessonType(lessonDTO.getLessonType());
            lessonType= lessonTypeRepository.save(lessonType);
        }
        //thiết lập lessonType cho lesson
        Lesson lesson = EntityMapper.INSTANCE.lessonDTOToLesson(lessonDTO);
        lesson.setLessonType(lessonType);

        lesson= lessonRepository.save(lesson);
        return EntityMapper.INSTANCE.lessonToLessonDTO(lesson);
    }

    public QuizDTO createQuiz(Long lessonId, QuizDTO quizDTO)
    {
        Lesson lesson= lessonRepository.findById(lessonId).orElseThrow(()->new RuntimeException("Lesson not found"));
        Quiz quiz= EntityMapper.INSTANCE.quizDTOToQuiz(quizDTO);
        quiz.setLesson(lesson);
        quiz= quizRepository.save(quiz);
        return  EntityMapper.INSTANCE.quizToQuizDTO(quiz);
    }

    //list lesson
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(EntityMapper.INSTANCE::lessonToLessonDTO)
                .collect(Collectors.toList());
    }

    //list lessonType
    public List<LessonTypeDTO> getLessonType()
    {
        List<LessonType> lessonType = lessonTypeRepository.findAll();
        return lessonType.stream()
                .map(EntityMapper.INSTANCE::lessonTypeToLessonTypeDTO)
                .collect(Collectors.toList());
    }

    //lấy lesson thuộc lessonType
    public  List<LessonDTO> getLessonsByType(String typeName)
    {
        LessonType lessonType = lessonTypeRepository.findByTypeName(typeName);
        if (lessonType == null)
        {
            throw new RuntimeException("LessonType not found");
        }

        List<Lesson> lessons = lessonRepository.findByLessonType(lessonType);
        return lessons.stream()
                .map(EntityMapper.INSTANCE::lessonToLessonDTO)
                .collect(Collectors.toList());
    }
}

