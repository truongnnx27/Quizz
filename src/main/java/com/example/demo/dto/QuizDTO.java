package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;
    private String title;
    private Integer totalPoint;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<QuestionDTO> questions;
    private Long lessonId;
}
