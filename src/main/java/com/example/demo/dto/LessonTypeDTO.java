package com.example.demo.dto;

import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class LessonTypeDTO {
    private Long id;
    private String typeName;
    private Set<LessonDTO> lessons; // có thể dùng Set thay List để tránh lặp
}
