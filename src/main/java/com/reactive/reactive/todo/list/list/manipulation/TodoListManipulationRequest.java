package com.reactive.reactive.todo.list.list.manipulation;

import com.reactive.reactive.todo.list.model.Action;
import lombok.Getter;

import java.util.HashSet;
import java.util.UUID;

@Getter
public class TodoListManipulationRequest {
    public UUID uuid;
    public String title;
    public HashSet<Action> actions;
}
