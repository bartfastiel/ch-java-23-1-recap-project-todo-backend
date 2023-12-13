package com.example.chjava231recapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.chjava231recapprojecttodobackend.UndoAction.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repo;
    private final UndoRepository undoRepo;

    public Todo save(NewTodo newTodo) {
        Todo todo = new Todo(UUID.randomUUID().toString(), newTodo.description(), newTodo.status());
        undoRepo.save(new UndoStep(INSERT, todo));
        return repo.save(todo);
    }

    public List<Todo> findAll() {
        return repo.findAll();
    }

    public Todo findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Todo save(Todo todo) {
        Optional<Todo> existing = repo.findById(todo.id());
        existing.ifPresentOrElse(
                t -> undoRepo.save(new UndoStep(UPDATE, t)),
                () -> undoRepo.save(new UndoStep(INSERT, todo))
        );
        return repo.save(todo);
    }

    public void deleteById(String id) {
        Optional<Todo> existing = repo.findById(id);
        if (!existing.isPresent()) return;
        undoRepo.save(new UndoStep(DELETE, existing.get()));
        repo.deleteById(id);
    }

    public void undo() {
        UndoStep step = undoRepo.findTopByOrderByTimestampDesc();
        if (step == null) return;
        switch (step.action()) {
            case INSERT -> repo.deleteById(step.item().id());
            case UPDATE -> repo.save(step.item());
            case DELETE -> repo.insert(step.item());
        }
        undoRepo.delete(step);
    }
}
