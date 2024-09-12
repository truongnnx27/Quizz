package com.example.demo.service;

import com.example.demo.MapStruct.EntityMapper;
import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.OptionDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Option;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionType;
import com.example.demo.entity.Quiz;
import com.example.demo.reponsitory.OptionRepository;
import com.example.demo.reponsitory.QuestionReponsitory;
import com.example.demo.reponsitory.QuestionTypeRepository;
import com.example.demo.reponsitory.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizzService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionReponsitory questionReponsitory;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private OptionRepository optionRepository;

    public QuestionDTO createQuestion(Long quizId, QuestionDTO questionDTO) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Chuyển DTO thành Entity
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setQuiz(quiz);

        // Thêm QuestionType vào Question
        QuestionType questionType = questionTypeRepository.findByTypeName(questionDTO.getQuestionType().getTypeName());
        question.setQuestionType(questionType);

        // Thêm các lựa chọn vào câu hỏi
        List<Option> options = new ArrayList<>();
        for (OptionDTO optionDTO : questionDTO.getOptions()) {
            Option option = new Option();
            option.setOptionText(optionDTO.getOptionText());
            option.setCorrect(optionDTO.isCorrect());
            option.setQuestion(question);
            options.add(option);
        }   
        question.setOptions(options);

        // Lưu câu hỏi và các lựa chọn
        question = questionReponsitory.save(question);
        optionRepository.saveAll(options);

        return EntityMapper.INSTANCE.questionToQuestionDTO(question);
    }

    public int calculateScore(Long quizId, List<AnswerDTO> answers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questions = quiz.getQuestions();
        int totalQuestions = questions.size();
        int score = 0;

        // Tính điểm cho mỗi câu trả lời đúng
        for (AnswerDTO answer : answers) {
            Question question = questions.stream()
                    .filter(q -> q.getId().equals(answer.getQuestionId()))
                    .findFirst()
                    .orElse(null);
            if (question != null) {
                // Lấy danh sách đáp án đúng
                List<Option> correctOptions = question.getOptions().stream()
                        .filter(Option::isCorrect)
                        .collect(Collectors.toList());

                // Kiểm tra xem người dùng đã chọn đúng tất cả các đáp án hay không
                boolean isCorrect = correctOptions.stream()
                        .allMatch(correctOption -> answer.getSelectedOptionIds().contains(correctOption.getId()));

                // Kiểm tra nếu số lượng đáp án đã chọn bằng với số lượng đáp án đúng
                isCorrect = isCorrect && correctOptions.size() == answer.getSelectedOptionIds().size();

                if (isCorrect) {
                    // Tính điểm cho câu trả lời đúng, ở đây 10 điểm cho mỗi câu
                    score += (int) Math.round(10.0 / totalQuestions);
                }
            }
        }

        return score;
    }

    public List<QuestionDTO> getQuestionsByQuizId(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        return quiz.getQuestions().stream()
                .map(EntityMapper.INSTANCE::questionToQuestionDTO)
                .collect(Collectors.toList());
    }

    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(EntityMapper.INSTANCE::quizToQuizDTO)
                .collect(Collectors.toList());
    }
}
