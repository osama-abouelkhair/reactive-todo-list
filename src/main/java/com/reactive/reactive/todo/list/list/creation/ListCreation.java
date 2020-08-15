package com.reactive.reactive.todo.list.list.creation;

import com.reactive.reactive.todo.list.model.TodoList;
import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ListCreation {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TodoListRepository todoListRepository;

    public Mono<TodoListCreationResponse> create(TodoListCreationRequest todoListDTO) {
        return todoListRepository.save(modelMapper.map(todoListDTO, TodoList.class))
                .flatMap(todoList -> Mono.just(modelMapper.map(todoList, TodoListCreationResponse.class)));
    }
}
