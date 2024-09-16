package com.example.demo.dto;

import lombok.Data;

@Data
public class OptionDTO {
    private Long id;
    private String optionText;
    private boolean isCorrect;
    private Long questionId;
}
