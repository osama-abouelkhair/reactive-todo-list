package com.reactive.reactive.todo.list.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table("action")
public class Action {
    @Id
    private Long id;
    private String description;
    private Long listId;

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
