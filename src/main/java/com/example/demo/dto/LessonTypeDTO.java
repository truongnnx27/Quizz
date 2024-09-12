package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonTypeDTO {
    private Long id;
    private String typeName;
    private List<LessonDTO> lessons; // Đây có thể gây vòng lặp nếu LessonDTO lại chứa LessonTypeDTO
}
