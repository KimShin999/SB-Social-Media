package com.example.demo.service.relationship;

import com.example.demo.model.Relationship;

import java.util.Optional;

public interface IRelationshipService {
    Iterable<Relationship> findAll();
    Relationship save (Relationship relationship);
    Relationship remove (Long id);
    Optional<Relationship> findById(Long id);
    Iterable<Relationship> getAllByFirstUserIdOrSecondUserId(Long Id1,Long Id2);
    Optional<Relationship> findByFirstUserIdAndSecondUserId(Long Id1,Long Id2);
    Iterable<Relationship> getAllBySecondUserId(Long id);
    void removeByFirstUserIdAndSecondUserId(Long id1, Long id2);
}
