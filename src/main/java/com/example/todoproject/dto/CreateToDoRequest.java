package com.example.todoproject.dto;

import com.example.todoproject.model.Status;
import com.example.todoproject.model.ToDo;

public record CreateToDoRequest(String description, Status status) {

    public ToDo toModel() {
        return ToDo.builder()
                .description(description)
                .status(status)
                .build();
    }
}
