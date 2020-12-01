package com.example.demo.service.status;
import com.example.demo.model.Status;
import com.example.demo.repository.Relationship.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements IStatusService{

    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status remove(Long id) {
        Status status = statusRepository.findById(id).get();
        statusRepository.deleteById(id);
        return status;
    }

    @Override
    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }
}
