package com.example.demo.service;

import com.example.demo.MapStruct.EntityMapper;
import com.example.demo.dto.OptionDTO;
import com.example.demo.entity.Option;
import com.example.demo.reponsitory.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    //list options
    public List<OptionDTO> getAllOptions() {
        List<Option> options = optionRepository.findAll();
        return options.stream()
                .map(EntityMapper.INSTANCE::optionToOptionDTO)
                .collect(Collectors.toList());
    }
}
