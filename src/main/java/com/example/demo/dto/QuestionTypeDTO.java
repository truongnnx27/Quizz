package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTypeDTO {
    private Long id;
    private String typeName;
    private List<QuestionDTO> questions;
}
