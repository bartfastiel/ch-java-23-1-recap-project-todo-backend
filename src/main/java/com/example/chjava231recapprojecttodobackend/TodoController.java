package com.example.chjava231recapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    Todo save(@RequestBody NewTodo todo) {
        return service.save(todo);
    }

    @GetMapping
    List<Todo> findAll() {
        return service.findAll();
    }
}
