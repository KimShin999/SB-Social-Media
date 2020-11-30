package com.example.demo.service.status;

import com.example.demo.model.Relationship;
import com.example.demo.model.Status;

import java.util.Optional;

public interface IStatusService {
    Iterable<Status> findAll();
    Status save (Status status);
    Status remove (Long id);
    Optional<Status> findById(Long id);
}
