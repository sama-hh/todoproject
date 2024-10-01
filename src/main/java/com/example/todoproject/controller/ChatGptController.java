package com.example.todoproject.controller;


import com.example.todoproject.service.InitializeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/initialize")
@RequiredArgsConstructor
public class ChatGptController {

    private final InitializeService initializeService;

    @GetMapping("/{topic}")
    String categorizeIngredient(@PathVariable String topic) {
        return initializeService.initialize(topic);
    }

}