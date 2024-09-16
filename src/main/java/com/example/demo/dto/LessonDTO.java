package com.example.demo.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private int duration;
    private int numberOfAttachments;
    private Long lessonTypeId;  // Chỉ cần ID của LessonType khi thêm mới
}
