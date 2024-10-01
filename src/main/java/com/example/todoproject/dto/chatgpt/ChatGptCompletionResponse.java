package com.example.todoproject.dto.chatgpt;

import java.util.List;

public record ChatGptCompletionResponse(List<ChatGptChoice> choices) {}