package com.example.demo.controller;

import com.example.demo.dto.QuizDTO;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz/api")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/createQuiz")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO)
    {
        QuizDTO createQuiz = quizzService.createQuiz(quizDTO);
        return ResponseEntity.ok(createQuiz);
    }

    // API để tính điểm cho quiz
    @PostMapping("/{quizId}/calculate-score")
    public ResponseEntity<Double> calculateQuizScore(
            @PathVariable Long quizId,
            @RequestBody List<Long> selectedOptionIds
    ) {
        double score = questionService.calculateScoreForQuiz(quizId, selectedOptionIds);
        return ResponseEntity.ok(score);
    }
}
