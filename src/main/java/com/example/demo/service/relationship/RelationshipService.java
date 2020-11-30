package com.example.demo.service.relationship;
import com.example.demo.model.Relationship;
import com.example.demo.repository.Relationship.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RelationshipService implements IRelationshipService {

    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Override
    public Iterable<Relationship> findAll() {
        return relationshipRepository.findAll();
    }

    @Override
    public Relationship save(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Override
    public Relationship remove(Long id) {
        Relationship relationship = relationshipRepository.findById(id).get();
        relationshipRepository.deleteById(id);
        return relationship;
    }

    @Override
    public Optional<Relationship> findById(Long id) {
        return relationshipRepository.findById(id);
    }
}
