package com.example.todoproject.service;

import com.example.todoproject.client.ChatGptApiClient;
import com.example.todoproject.dto.chatgpt.ChatGptCompletionRequest;
import com.example.todoproject.dto.chatgpt.ChatGptCompletionResponse;
import com.example.todoproject.dto.chatgpt.ChatGptMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitializeService {
    private final ChatGptApiClient chatGptApiClient;

    public String initialize(String topic) {
        String messageContent = "Generate approximately 20-30 new todo items for " + topic;

        ChatGptCompletionResponse response = chatGptApiClient.complete(
                new ChatGptCompletionRequest(
                        "gpt-4o-mini",
                        List.of(new ChatGptMessage("user", messageContent))
                )
        );
        return response.choices().getFirst().message().content();
    }
}
