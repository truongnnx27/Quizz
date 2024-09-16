package com.example.demo.MapStruct;

import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    @Mapping(source = "lesson.id", target = "lessonId")
    QuizDTO quizToQuizDTO(Quiz quiz);

    @Mapping(source = "lessonId", target = "lesson.id")
    Quiz quizDTOToQuiz(QuizDTO quizDTO);
}
