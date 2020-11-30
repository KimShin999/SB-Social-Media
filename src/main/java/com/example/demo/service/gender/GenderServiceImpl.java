package com.example.demo.service.gender;

import com.example.demo.model.Gender;
import com.example.demo.repository.gender.IGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements IGenderService {
    @Autowired
    private IGenderRepository genderRepository;
    @Override
    public Iterable<Gender> findAll() {
        return genderRepository.findAll();
    }
}
