package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionTypeDTO {
    private Long id;
    private String typeName;
    private List<QuestionDTO> questions;
}
