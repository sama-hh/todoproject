package com.example.todoproject.dto.chatgpt;

import java.util.List;

public record ChatGptCompletionRequest(String model, List<ChatGptMessage> messages) {}