package com.example.todoproject.model;

import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todo")
@With
@Builder
public record ToDo(String id, String description, Status status) {
}
