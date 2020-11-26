package com.example.demo.repository.user;

import com.example.demo.model.AppRole;
import com.example.demo.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(ERole name);
}
