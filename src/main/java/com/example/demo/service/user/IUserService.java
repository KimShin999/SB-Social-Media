package com.example.demo.service.user;

import com.example.demo.model.AppUser;

import java.util.Optional;

public interface IUserService {
    Iterable<AppUser> findAll();
    AppUser save ( AppUser user);
    AppUser remove (Long id);
    Optional<AppUser> findById(Long id);
}
