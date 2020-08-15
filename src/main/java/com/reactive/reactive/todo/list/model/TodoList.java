package com.reactive.reactive.todo.list.model;

import java.util.*;

import lombok.AllArgsConstructor;
import  lombok.Getter;
import lombok.NoArgsConstructor;
import  lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table("todo_list")
public class TodoList {
    @Id
    private Long id;
    private UUID uuid = UUID.randomUUID();
    private String title;

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", title=" + title +
                '}';
    }
}
