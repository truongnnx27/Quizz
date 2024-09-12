package com.example.demo.dto;

import java.util.List;

public class AnswerDTO {
    private Long questionId; // ID của câu hỏi
    private List<Long> selectedOptionIds; // Danh sách ID của các lựa chọn đã chọn

    // Getters và Setters
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getSelectedOptionIds() {
        return selectedOptionIds;
    }

    public void setSelectedOptionIds(List<Long> selectedOptionIds) {
        this.selectedOptionIds = selectedOptionIds;
    }
}
