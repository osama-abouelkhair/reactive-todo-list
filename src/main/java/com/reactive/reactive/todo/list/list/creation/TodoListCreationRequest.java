package com.reactive.reactive.todo.list.list.creation;

import com.reactive.reactive.todo.list.model.Action;
import lombok.Getter;

import java.util.Set;

@Getter
public class TodoListCreationRequest {
    public String title;
    public Set<Action> actions;
}
