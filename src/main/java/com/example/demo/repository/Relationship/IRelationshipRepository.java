package com.example.demo.repository.Relationship;
import com.example.demo.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> getAllByFirstUserIdOrSecondUserId(Long Id1,Long Id2);
    Iterable<Relationship> getAllBySecondUserId(Long id);
    Optional<Relationship> findByFirstUserIdAndSecondUserId(Long Id1, Long Id2);
    void removeByFirstUserIdAndSecondUserId(Long id1, Long id2);
}
