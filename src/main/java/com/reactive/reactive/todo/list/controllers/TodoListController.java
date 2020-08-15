package com.reactive.reactive.todo.list.controllers;

import com.reactive.reactive.todo.list.list.creation.ListCreation;
import com.reactive.reactive.todo.list.list.creation.TodoListCreationRequest;
import com.reactive.reactive.todo.list.list.creation.TodoListCreationResponse;
import com.reactive.reactive.todo.list.list.manipulation.ListManipulation;
import com.reactive.reactive.todo.list.list.manipulation.TodoListManipulationRequest;
import com.reactive.reactive.todo.list.list.manipulation.TodoListManipulationResponse;
import com.reactive.reactive.todo.list.model.TodoList;
import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("/lists")
public class TodoListController {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    ListCreation listCreation;

    @Autowired
    ListManipulation listManipulation;

    @GetMapping
    private Flux<TodoList> getAllLists() {
        return todoListRepository.findAll();
    }

    @GetMapping("/{id}")
    private Mono<TodoList> getListById(@PathVariable UUID id) {
        return todoListRepository.findByUuid(id);
    }

    @PostMapping
    private Mono<TodoListCreationResponse> addList(@RequestBody TodoListCreationRequest todoListDTO) {
        return listCreation.create(todoListDTO);
    }

    @DeleteMapping("/{id}")
    private Mono<Void> deleteList(@PathVariable UUID id) {
        return todoListRepository.deleteByUuid(id);
    }

    @PutMapping
    private Mono<TodoListManipulationResponse> edit(@RequestBody TodoListManipulationRequest todoListManipulationRequest) {
        return listManipulation.edit(todoListManipulationRequest);
    }
}
