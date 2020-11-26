package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser remove(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).get();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }
}