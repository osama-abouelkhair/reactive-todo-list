package com.reactive.reactive.todo.list.list.creation;


import lombok.Setter;

import java.util.UUID;

@Setter
public class TodoListCreationResponse {
    public UUID uuid;
    public String title;
}
