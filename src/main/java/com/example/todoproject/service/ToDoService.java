package com.example.todoproject.service;

import com.example.todoproject.dto.UpdateToDoRequest;
import com.example.todoproject.model.ToDo;
import com.example.todoproject.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final IdService Idservice;
    private final TextCorrectionService textCorrectionService;

    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public ToDo createToDo(ToDo todo) {
        String generatedId = Idservice.randomId();

        String correctedDescription = textCorrectionService.correctText(todo.description());

        ToDo newToDo = new ToDo(generatedId, correctedDescription, todo.status());
        return toDoRepository.save(newToDo);
    }

    public ToDo getToDoDetails(String id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        return todo.orElseThrow(() -> new NoSuchElementException("ToDo with id " + id + " not found"));
    }

    public ToDo updateToDo(String id, UpdateToDoRequest request) {
        ToDo existingToDo = toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ToDo not found"));

        ToDo updatedToDo = new ToDo(existingToDo.id(), request.description(), request.status());

        return toDoRepository.save(updatedToDo);
    }

    public void deleteToDo(String id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        if (todo.isPresent()) {
            toDoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("ToDo with id " + id + " not found");
        }
    }

}
