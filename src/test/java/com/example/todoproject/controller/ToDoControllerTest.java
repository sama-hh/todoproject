package com.example.todoproject.controller;

import com.example.todoproject.model.Status;
import com.example.todoproject.model.ToDo;
import com.example.todoproject.repository.ToDoRepository;
import com.example.todoproject.service.ToDoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ToDoService toDoService;
    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    @DirtiesContext
    void getTodos() throws Exception {
        ToDo todo = new ToDo("1", "make smth", Status.OPEN);
        toDoRepository.save(todo);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                     [
                                 {
                                    "id": "1",
                                    "description": "make smth",
                                    "status": "OPEN"
                                 }
                                ]
                                """
                ));
    }

    @Test
    @DirtiesContext
    void save() throws Exception {
        String ToDo = """
                    {
                      "description": "make smth",
                      "status": "OPEN"
                    }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ToDo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(ToDo));

    }

    @Test
    @DirtiesContext
    void getToDoDetails() throws Exception {
        ToDo todo = new ToDo("1", "make smth", Status.OPEN);
        toDoRepository.save(todo);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                 {
                                    "id": "1",
                                    "description": "make smth",
                                    "status": "OPEN"
                                 }
                                """
                ));

    }

    @Test
    @DirtiesContext
    void updateToDo() throws Exception {
        ToDo todo = new ToDo("1", "make smth", Status.OPEN);
        toDoRepository.save(todo);

        String updatedToDo = """
                    {
                      "description": "make 2",
                      "status": "OPEN"
                    }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedToDo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(updatedToDo));

    }

    @Test
    @DirtiesContext
    void deleteToDo() throws Exception {
        ToDo todo = new ToDo("1", "make smth", Status.OPEN);
        toDoRepository.save(todo);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        boolean exists = toDoRepository.existsById("1");
        Assertions.assertFalse(exists);
    }
}