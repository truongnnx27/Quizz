package com.example.demo.controller;

import com.example.demo.dto.AnswerDTO; // Thay đổi import
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuizDTO;
import com.example.demo.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuizController {

    @Autowired
    private QuizzService quizService;


    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizDTO>> getAllQuizz()
    {
        List<QuizDTO> quizze = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizze);
    }

    @PostMapping("quizzes/{quizId}/question")
    public  ResponseEntity<QuestionDTO> addQuestion(@PathVariable("quizId") Long quizId, @RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO createdQuestion = quizService.createQuestion(quizId,questionDTO);
        return ResponseEntity.ok(createdQuestion);
    }

    //get all question for quizzes
    @GetMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions(@PathVariable("quizId") Long quizId)
    {
         List<QuestionDTO> questions = quizService.getQuestionsByQuizId(quizId);
         return ResponseEntity.ok(questions);
    }

    //tính điểm
    @PostMapping("/quizzes/{quizId}/score")
    public ResponseEntity<Integer> calculateScore(@PathVariable Long quizId, @RequestBody List<AnswerDTO> answers)
    {
        int score = quizService.calculateScore(quizId,answers);
        return ResponseEntity.ok(score);
    }




}
