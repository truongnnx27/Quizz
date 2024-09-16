package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String commentText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //private UserDTO user; // tùy vào yêu cầu của bạn, có thể tránh map User để không bị lặp
}
