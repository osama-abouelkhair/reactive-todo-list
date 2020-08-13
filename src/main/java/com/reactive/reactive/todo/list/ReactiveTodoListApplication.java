package com.reactive.reactive.todo.list;

import com.reactive.reactive.todo.list.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveTodoListApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveTodoListApplication.class, args);
	}


	@Autowired
	TodoListRepository todoListRepository;

	@Override
	public void run(String... args) throws Exception {
	}
}
