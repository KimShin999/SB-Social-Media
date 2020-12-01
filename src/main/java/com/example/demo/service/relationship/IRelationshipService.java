package com.example.demo.service.relationship;

import com.example.demo.model.Relationship;

import java.util.Optional;

public interface IRelationshipService {
    Iterable<Relationship> findAll();
    Relationship save (Relationship relationship);
    Relationship remove (Long id);
    Optional<Relationship> findById(Long id);
}
