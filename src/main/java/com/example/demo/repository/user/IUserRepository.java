package com.example.demo.repository.user;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Iterable<AppUser> getAllByUsernameContaining(String name);
    Iterable<AppUser> getAllByFirstNameContaining(String name);
}
