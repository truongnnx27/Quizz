package com.example.demo.service;


import com.example.demo.MapStruct.OptionMapper;
import com.example.demo.dto.OptionDTO;
import com.example.demo.entity.Option;
import com.example.demo.entity.Question;
import com.example.demo.reponsitory.OptionRepository;
import com.example.demo.reponsitory.QuestionReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionReponsitory questionReponsitory;

    private final OptionMapper optionMapper = OptionMapper.INSTANCE;

    // Lấy tất cả các option
    public List<OptionDTO> getAllOptions() {
        List<Option> options = optionRepository.findAll();
        return options.stream()
                .map(optionMapper::optionToOptionDTO)
                .collect(Collectors.toList());
    }

    // Thêm một Option mới
    public OptionDTO createOption(OptionDTO optionDTO) {
        Optional<Question> questionOptional = questionReponsitory.findById(optionDTO.getQuestionId());

        if (questionOptional.isPresent()) {
            Option option = optionMapper.optionDTOToOption(optionDTO);
            option.setQuestion(questionOptional.get());
            Option savedOption = optionRepository.save(option);
            return optionMapper.optionToOptionDTO(savedOption);
        } else {
            throw new RuntimeException("Question không tồn tại");
        }
    }

    // Cập nhật một Option
    public OptionDTO updateOption(Long id, OptionDTO optionDTO) {
        Optional<Option> existingOption = optionRepository.findById(id);

        if (existingOption.isPresent()) {
            Option option = existingOption.get();
            option.setOptionText(optionDTO.getOptionText());
            option.setCorrect(optionDTO.isCorrect());

            Optional<Question> questionOptional = questionReponsitory.findById(optionDTO.getQuestionId());
            questionOptional.ifPresent(option::setQuestion);

            Option updatedOption = optionRepository.save(option);
            return optionMapper.optionToOptionDTO(updatedOption);
        } else {
            throw new RuntimeException("Option không tồn tại");
        }
    }

    // Xoá một Option
    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

    // Lấy Option theo ID
    public OptionDTO getOptionById(Long id) {
        Optional<Option> option = optionRepository.findById(id);
        if (option.isPresent()) {
            return optionMapper.optionToOptionDTO(option.get());
        } else {
            throw new RuntimeException("Option không tồn tại");
        }
    }

}
