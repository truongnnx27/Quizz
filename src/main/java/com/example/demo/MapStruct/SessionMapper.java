package com.example.demo.MapStruct;

import com.example.demo.dto.SessionDTO;
import com.example.demo.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    // Ánh xạ Session sang SessionDTO
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "lesson.id", target = "lessonId")
    SessionDTO sessionToSessionDTO(Session session);

    // Ánh xạ SessionDTO sang Session
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "lessonId", target = "lesson.id")
    Session sessionDTOToSession(SessionDTO sessionDTO);
}
