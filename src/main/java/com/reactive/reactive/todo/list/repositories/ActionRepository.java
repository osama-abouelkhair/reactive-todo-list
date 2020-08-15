package com.reactive.reactive.todo.list.repositories;

import com.reactive.reactive.todo.list.model.Action;
import com.reactive.reactive.todo.list.model.TodoList;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActionRepository extends ReactiveCrudRepository<Action, Integer> {
        Flux<Action> findByListId(TodoList listId);

        @Modifying
        @Query("DELETE FROM action WHERE list_id = :listId")
        Mono<Void> deleteAllByListId(Long listId);

}

