package com.example.todoproject.controller;

import com.example.todoproject.dto.CreateToDoRequest;
import com.example.todoproject.dto.UpdateToDoRequest;
import com.example.todoproject.model.ToDo;
import com.example.todoproject.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getTodos() {
        return toDoService.getAllTodos();
    }

    @PostMapping
    public ToDo save(@RequestBody CreateToDoRequest request) {
        ToDo newToDo = toDoService.createToDo(request.toModel());
        return newToDo;
    }

    @GetMapping("/{id}")
    public ToDo getToDoDetails(@PathVariable String id) {
        return toDoService.getToDoDetails(id);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable String id, @RequestBody UpdateToDoRequest request) {
        return toDoService.updateToDo(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }
}
