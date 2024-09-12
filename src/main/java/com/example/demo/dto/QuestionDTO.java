package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private QuizDTO quiz;
    private QuestionTypeDTO questionType;
    private List<OptionDTO> options;
}

