package com.example.chjava231recapprojecttodobackend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UndoRepository extends MongoRepository<UndoStep, String> {
    UndoStep findTopByOrderByTimestampDesc();
}
