package com.example.chjava231recapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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

    public Todo findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Todo save(Todo todo) {
        return repo.save(todo);
    }
}
