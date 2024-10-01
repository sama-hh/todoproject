package com.example.todoproject.dto;

import com.example.todoproject.model.Status;
import com.example.todoproject.model.ToDo;

public record UpdateToDoRequest(String description, Status status) {
}
