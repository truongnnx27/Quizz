package com.example.demo.MapStruct;


import com.example.demo.dto.QuestionDTO;
import com.example.demo.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "questionType.id", target = "questionTypeId")
    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(source = "quizId", target = "quiz.id")
    @Mapping(source = "questionTypeId", target = "questionType.id")
    Question questionDTOToQuestion(QuestionDTO questionDTO);
}
