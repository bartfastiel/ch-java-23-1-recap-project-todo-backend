package com.example.chjava231recapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repo;

    public Todo save(NewTodo newTodo) {
        Todo todo = new Todo(UUID.randomUUID().toString(), newTodo.description(), newTodo.status());
        return repo.save(todo);
    }

    public List<Todo> findAll() {
        return repo.findAll();
    }
}
