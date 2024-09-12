package com.example.demo.service;

import com.example.demo.MapStruct.EntityMapper;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuestionTypeDTO;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionType;
import com.example.demo.reponsitory.QuestionReponsitory;
import com.example.demo.reponsitory.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private QuestionReponsitory questionReponsitory;

    //list question
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionReponsitory.findAll();

        return questions.stream()
                .map(EntityMapper.INSTANCE::questionToQuestionDTO)
                .collect(Collectors.toList());
    }

    //list QuestionType
    public List<QuestionTypeDTO> getQuestionType()
    {
        List<QuestionType> questionType = questionTypeRepository.findAll();

        return questionType.stream()
                .map(EntityMapper.INSTANCE::questionTypeToQuestionTypeDTO)
                .collect(Collectors.toList());
    }
}
