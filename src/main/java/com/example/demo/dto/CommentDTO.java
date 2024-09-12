package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long userId; // Để lưu ID của người dùng đã bình luận
    private Long lessonId; // Để lưu ID của bài học
    private String commentText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
