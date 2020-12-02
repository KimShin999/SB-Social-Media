package com.example.demo.repository.Relationship;
import com.example.demo.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
    Iterable<Relationship> getAllByFirstUserIdOrSecondUserId(Long Id1,Long Id2);
}
