package com.reactive.reactive.todo.list.list.manipulation;

import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
public class TodoListManipulationResponse {
    public UUID uuid;
    public String title;
    public Set<ActionDTO> actions;
}