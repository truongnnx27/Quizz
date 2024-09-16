package com.example.demo.MapStruct;

import com.example.demo.dto.LessonTypeDTO;
import com.example.demo.entity.LessonType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonTypeMapper {
    @Mapping(target = "lessons", ignore = true) // Bỏ qua lessons để tránh lặp vô hạn
    LessonTypeDTO lessonTypeToLessonTypeDTO(LessonType lessonType);

    @Mapping(target = "lessons", ignore = true)
    LessonType lessonTypeDTOToLessonType(LessonTypeDTO lessonTypeDTO);
}