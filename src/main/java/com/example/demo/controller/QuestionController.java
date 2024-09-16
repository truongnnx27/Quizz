package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO createQuestion = questionService.createQuestion(questionDTO);
        return ResponseEntity.ok(createQuestion);
    }
}
