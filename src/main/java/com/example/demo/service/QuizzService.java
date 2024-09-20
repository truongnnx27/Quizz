package com.example.demo.service;

import com.example.demo.MapStruct.QuizMapper;
import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Quiz;
import com.example.demo.reponsitory.QuizRepository;
import com.example.demo.reponsitory.lessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizzService {

    @Autowired
    private lessonRepository lessonDao;

    @Autowired
    private QuizRepository quizRepository;

    private final QuizMapper quizMapper = QuizMapper.INSTANCE;

    public QuizDTO createQuiz(QuizDTO quizDTO)
    {
        Lesson lesson = lessonDao.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Quiz quiz = quizMapper.quizDTOToQuiz(quizDTO);
        quiz.setLesson(lesson);

        Quiz saveQuiz = quizRepository.save(quiz);

        return quizMapper.quizToQuizDTO(saveQuiz);
    }

    public List<QuizDTO> getAllQuiz()
    {
        List<Quiz> quizzed = quizRepository.findAll();
        return  quizzed.stream().map(quizMapper::quizToQuizDTO).collect(Collectors.toList());
    }
}
