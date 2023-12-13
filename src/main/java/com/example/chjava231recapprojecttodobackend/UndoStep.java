package com.example.chjava231recapprojecttodobackend;

import java.time.Instant;
import java.util.UUID;

public record UndoStep(
        String id,
        Instant timestamp,
        UndoAction action,
        Todo item
) {
    UndoStep(UndoAction action, Todo item) {
        this(UUID.randomUUID().toString(), Instant.now(), action, item);
    }
}
