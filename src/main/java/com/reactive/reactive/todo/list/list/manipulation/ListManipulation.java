package com.reactive.reactive.todo.list.list.manipulation;

import com.reactive.reactive.todo.list.model.Action;
import com.reactive.reactive.todo.list.repositories.ActionRepository;
import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class ListManipulation {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ModelMapper modelMapper;

    public Mono<TodoListManipulationResponse> edit(TodoListManipulationRequest todoListManipulationRequest) {
        return todoListRepository.findByUuid(todoListManipulationRequest.uuid)
                .flatMap(todoList -> {
                    actionRepository.deleteAllByListId(todoList.getId()).subscribe();
                    todoListManipulationRequest.actions.forEach(action -> action.setListId(todoList.getId()));
                    Flux<Action> savedActions = actionRepository.saveAll(todoListManipulationRequest.actions);
                    todoList.setTitle(todoListManipulationRequest.title);
                    return todoListRepository
                            .save(todoList)
                            .flatMap(savedTodoList -> {
                                TodoListManipulationResponse todoListManipulationResponse =
                                        modelMapper.map(savedTodoList, TodoListManipulationResponse.class);
                                return savedActions.collectList().flatMap(actions -> {
                                    todoListManipulationResponse.setActions(
                                            actions
                                                    .stream()
                                                    .map(action -> modelMapper.map(action, ActionDTO.class))
                                                    .collect(Collectors.toSet())
                                    );
                                    return Mono.just(todoListManipulationResponse);
                                });
                            });
                });
    }
}
