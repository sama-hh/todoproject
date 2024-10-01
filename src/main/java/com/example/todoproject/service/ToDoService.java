package com.example.todoproject.service;

import com.example.todoproject.model.ToDo;
import com.example.todoproject.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public ToDo createToDo(ToDo todo) {
        ToDo newToDo = new ToDo(todo.description(), todo.status());
        return toDoRepository.save(newToDo);
    }

}
