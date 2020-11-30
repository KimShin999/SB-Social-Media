package com.example.demo.repository.Relationship;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status, Long> {
}
