package com.example.demo.controller;

import com.example.demo.dto.OptionDTO;
import com.example.demo.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options/api")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public List<OptionDTO> getAllOptions() {
        return optionService.getAllOptions();
    }

    @PostMapping("/createOption")
    public ResponseEntity<OptionDTO> createOption(@RequestBody OptionDTO optionDTO) {
        OptionDTO createdOption = optionService.createOption(optionDTO);
        return ResponseEntity.ok(createdOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> updateOption(@PathVariable Long id, @RequestBody OptionDTO optionDTO) {
        OptionDTO updatedOption = optionService.updateOption(id, optionDTO);
        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDTO> getOptionById(@PathVariable Long id) {
        OptionDTO optionDTO = optionService.getOptionById(id);
        return ResponseEntity.ok(optionDTO);
    }
}
