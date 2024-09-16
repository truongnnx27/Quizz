package com.example.demo.MapStruct;

import com.example.demo.dto.OptionDTO;
import com.example.demo.entity.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OptionMapper {
    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    @Mapping(source = "question.id", target = "questionId")
    OptionDTO optionToOptionDTO(Option option);

    @Mapping(source = "questionId", target = "question.id")
    Option optionDTOToOption(OptionDTO optionDTO);
}
