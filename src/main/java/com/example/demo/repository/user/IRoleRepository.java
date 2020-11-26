package com.example.demo.repository.user;

import com.example.demo.model.AppRoLe;
import com.example.demo.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<AppRoLe, Long> {
    Optional<AppRoLe> findByName(ERole name);
}
