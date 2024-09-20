package com.example.demo.service;

import com.example.demo.MapStruct.OptionMapper;
import com.example.demo.MapStruct.QuestionMapper;
import com.example.demo.MapStruct.QuestionTypeMapper;
import com.example.demo.MapStruct.QuizMapper;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.QuestionTypeDTO;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionReponsitory questionReponsitory;

    @Autowired
    private OptionRepository optionRepository;

    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;
    private final OptionMapper optionMapper = OptionMapper.INSTANCE;
    private final QuestionTypeMapper questionTypeMapper = QuestionTypeMapper.INSTANCE;

    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        // Lấy Quiz và QuestionType từ cơ sở dữ liệu theo ID từ DTO
        Quiz quiz = quizRepository.findById(questionDTO.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        QuestionType questionType = questionTypeRepository.findById(questionDTO.getQuestionTypeId())
                .orElseThrow(() -> new RuntimeException("QuestionType not found"));

        // Ánh xạ DTO sang Entity của Question
        Question question = questionMapper.questionDTOToQuestion(questionDTO);
        question.setQuiz(quiz);
        question.setQuestionType(questionType);

        // Lưu Question trước
        Question createdQuestion = questionReponsitory.save(question);

        // Ánh xạ và lưu danh sách Options
        List<Option> options = questionDTO.getOptions().stream()
                .map(optionDTO -> {
                    Option option = optionMapper.optionDTOToOption(optionDTO);
                    option.setQuestion(createdQuestion); // Liên kết Option với Question vừa được lưu
                    return option;
                }).collect(Collectors.toList());

        // Lưu toàn bộ Options
        optionRepository.saveAll(options);

        // Trả về QuestionDTO đã được lưu với đầy đủ thông tin
        QuestionDTO savedQuestionDTO = questionMapper.questionToQuestionDTO(createdQuestion);

        // Đảm bảo thêm các Option vào DTO trả về
        savedQuestionDTO.setOptions(
                options.stream()
                        .map(optionMapper::optionToOptionDTO)
                        .collect(Collectors.toList())
        );

        return savedQuestionDTO;
    }

    public double calculateScoreForQuiz(Long quizId, List<Long> selectedOptionIds)
    {
        // Lấy danh sách các câu hỏi của quiz theo quizId
        List<Question> questions = questionReponsitory.findByQuizId(quizId);

        // Tính số lượng câu hỏi trong quiz
        int numberOfQuestions = questions.size();

        // Nếu không có câu hỏi nào, trả về 0 điểm
        if (numberOfQuestions == 0) {
            return 0.0;
        }

        // Tính điểm mỗi câu hỏi dựa trên thang điểm 10
        double pointPerQuestion = 10.0 / numberOfQuestions;

        // Tổng điểm của người dùng đạt được
        double userPoints = 0;

        // Lặp qua từng câu hỏi và so sánh đáp án
        for (int i = 0; i < numberOfQuestions; i++) {
            Question question = questions.get(i);
            Long selectedOptionId = selectedOptionIds.get(i);

            // Tìm đáp án đúng của câu hỏi
            Option correctOption = question.getOptions().stream()
                    .filter(Option::isCorrect)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Correct option not found for question id: " + question.getId()));

            // Nếu đáp án người dùng chọn là đúng
            if (correctOption.getId().equals(selectedOptionId)) {
                // Cộng điểm tương ứng cho câu hỏi đó
                userPoints += pointPerQuestion;
            }
        }

        // Làm tròn điểm của người dùng lên 2 chữ số sau dấu phẩy
        return Math.round(userPoints * 100.0) / 100.0;
    }

    public  List<QuestionTypeDTO> getAllQuestionType()
    {
        List<QuestionType> questionType = questionTypeRepository.findAll();
        return questionType.stream().map(questionTypeMapper::questionTypeToQuestionTypeDTO).collect(Collectors.toList());

    }

    public List<QuestionDTO> getAllQuestion()
    {
        List<Question> question = questionReponsitory.findAll();
        return question.stream().map(questionMapper::questionToQuestionDTO).collect(Collectors.toList());
    }
}
