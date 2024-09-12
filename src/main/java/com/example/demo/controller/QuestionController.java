package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuestionTypeDTO;
import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionsService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestion()
    {
        List<QuestionDTO> quesstion = questionService.getAllQuestions();
        return ResponseEntity.ok(quesstion);
    }

    @GetMapping("questionsType")
    public ResponseEntity<List<QuestionTypeDTO>> getAllQuestionType()
    {
        List<QuestionTypeDTO> questionType= questionService.getQuestionType();
        return ResponseEntity.ok(questionType);
    }


}
