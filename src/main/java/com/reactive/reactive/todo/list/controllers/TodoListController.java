package com.reactive.reactive.todo.list.controllers;

import com.reactive.reactive.todo.list.model.TodoList;
import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;


@RestController
@RequestMapping("/lists")
public class TodoListController {

    @Autowired
    TodoListRepository todoListRepository;

    @GetMapping("/{id}")
    private Flux<TodoList> getEmployeeById(@PathVariable UUID id) {
        return todoListRepository.findByUuid(id);
    }
}
