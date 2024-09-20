package com.example.demo.controller;

import com.example.demo.dto.OptionDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuestionTypeDTO;
import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.service.OptionService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizzService quizzService;

    @Autowired
    private OptionService optionService;

    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO createQuestion = questionService.createQuestion(questionDTO);
        return ResponseEntity.ok(createQuestion);
    }

    @GetMapping("/getAllQuestion")
    public ResponseEntity<List<QuestionDTO>> getAllQuestion()
    {
        List<QuestionDTO> getAllQuestion = questionService.getAllQuestion();
        return ResponseEntity.ok(getAllQuestion);
    }

    @GetMapping("/getAllQuestionType")
    public ResponseEntity<List<QuestionTypeDTO>> getAllQuestionType()
    {
        List<QuestionTypeDTO> getAllQuestionTypes = questionService.getAllQuestionType();
        return ResponseEntity.ok(getAllQuestionTypes);
    }

    @GetMapping("/getALlQuizz")
    public ResponseEntity<List<QuizDTO>> getAllQUizz()
    {
        List<QuizDTO> quizzed = quizzService.getAllQuiz();
        return ResponseEntity.ok(quizzed);
    }

    @GetMapping("/getAllOption")
    public List<OptionDTO> getAllOptions() {
        return optionService.getAllOptions();
    }

    @PostMapping("/createOption")
    public ResponseEntity<OptionDTO> createOption(@RequestBody OptionDTO optionDTO) {
        OptionDTO createdOption = optionService.createOption(optionDTO);
        return ResponseEntity.ok(createdOption);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OptionDTO> updateOption(@PathVariable Long id, @RequestBody OptionDTO optionDTO) {
        OptionDTO updatedOption = optionService.updateOption(id, optionDTO);
        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByOptionId/{id}")
    public ResponseEntity<OptionDTO> getOptionById(@PathVariable Long id) {
        OptionDTO optionDTO = optionService.getOptionById(id);
        return ResponseEntity.ok(optionDTO);
    }
}
