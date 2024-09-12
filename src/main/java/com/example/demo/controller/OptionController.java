package com.example.demo.controller;

import com.example.demo.dto.OptionDTO;
import com.example.demo.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/options")
    public ResponseEntity<List<OptionDTO>> getAlloptions()
    {
        List<OptionDTO> options = optionService.getAllOptions();
        return ResponseEntity.ok(options);
    }
}
