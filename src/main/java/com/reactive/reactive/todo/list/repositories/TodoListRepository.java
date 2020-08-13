package com.reactive.reactive.todo.list.repositories;

import com.reactive.reactive.todo.list.model.TodoList;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TodoListRepository extends ReactiveCrudRepository<TodoList, Integer> {

    Mono<TodoList> findByUuid(UUID uuid);

    Mono<Void> deleteByUuid(UUID id);

}

