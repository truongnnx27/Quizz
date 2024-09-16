package com.example.demo.MapStruct;

import com.example.demo.dto.QuestionTypeDTO;
import com.example.demo.entity.QuestionType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionTypeMapper {
    QuestionTypeMapper INSTANCE = Mappers.getMapper(QuestionTypeMapper.class);

    QuestionTypeDTO questionTypeToQuestionTypeDTO(QuestionType questionType);

    QuestionType questionTypeDTOToQuestionType(QuestionTypeDTO questionTypeDTO);
}