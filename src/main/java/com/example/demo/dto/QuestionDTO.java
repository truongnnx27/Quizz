package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String content;
    private int points;
    private String questionText;
    private Long quizId; // ID của Quiz mà câu hỏi liên kết
    private Long questionTypeId; // ID của QuestionType
    private List<OptionDTO> options;
}
