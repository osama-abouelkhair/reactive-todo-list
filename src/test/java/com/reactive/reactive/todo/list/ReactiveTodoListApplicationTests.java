package com.reactive.reactive.todo.list;

import com.reactive.reactive.todo.list.list.creation.TodoListCreationRequest;
import com.reactive.reactive.todo.list.list.creation.TodoListCreationResponse;
import com.reactive.reactive.todo.list.model.TodoList;
import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ReactiveTodoListApplicationTests {

    @Autowired
    ApplicationContext context;

    WebTestClient rest;

    @Autowired
    TodoListRepository todoListRepository;

    @BeforeEach
    public void init() {
        this.rest = WebTestClient
                .bindToApplicationContext(this.context)
                .configureClient()
                .build();
    }

    @Test
    void getSavedTodoListsTest() {
        TodoList todoList = new TodoList();
        todoList.setTitle("title1");
        todoListRepository.save(todoList).doOnSuccess(item -> {
            rest.get().uri("/lists/{id}", item.getUuid())
                    .header(HttpHeaders.ACCEPT, "application/json")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(TodoList.class).isEqualTo(todoList);
        });
    }

    @Test
    void listCreationTest() {
        TodoListCreationRequest todoListCreationRequest = new TodoListCreationRequest();
        todoListCreationRequest.title = "title1";
        TodoListCreationResponse todoListCreationResponse = new TodoListCreationResponse();
        todoListCreationResponse.title = todoListCreationRequest.title;
        rest.post().uri("/lists")
                .header(HttpHeaders.ACCEPT, "application/json")
                .bodyValue(todoListCreationRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TodoListCreationResponse.class)
                .consumeWith(expectedTodoListResponse -> {
                    assertThat(Objects.requireNonNull(expectedTodoListResponse.getResponseBody()).uuid).isNotNull();
                    assertThat(expectedTodoListResponse.getResponseBody().title).isEqualTo(todoListCreationRequest.title);
                });
    }

}
