package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private int duration;
    private int numberOfAttachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LessonTypeDTO lessonType; // Không nên chứa danh sách lessons
    private List<CommentDTO> comments;
    private List<QuizDTO> quizzes;
    private List<SessionDTO> sessions;
}


