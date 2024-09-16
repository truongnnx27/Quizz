package com.example.demo.dto;

import lombok.Data;

@Data
public class SessionDTO {
    private Long id;
    private String nameSessions;
    private Long courseId; // Sử dụng courseId thay vì ánh xạ toàn bộ Course entity
    private Long lessonId; // Sử dụng lessonId thay vì ánh xạ toàn bộ Lesson entity
}
