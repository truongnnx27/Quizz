package com.example.demo.MapStruct;

import com.example.demo.dto.LessonDTO;
import com.example.demo.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "lessonTypeId", target = "lessonType.id")
    Lesson lessonDTOToLesson(LessonDTO lessonDTO);

    @Mapping(source = "lessonType.id", target = "lessonTypeId")
    LessonDTO lessonToLessonDTO(Lesson lesson);
}
