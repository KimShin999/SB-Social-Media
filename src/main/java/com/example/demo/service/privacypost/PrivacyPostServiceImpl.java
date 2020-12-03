package com.example.demo.service.privacypost;

import com.example.demo.model.PrivacyPost;
import com.example.demo.repository.privacypost.IPrivacyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivacyPostServiceImpl implements IPrivacyPostService {
    @Autowired
    private IPrivacyPostRepository privacyPostRepository;
    @Override
    public Optional<PrivacyPost> findById(Long id) {
        return privacyPostRepository.findById(id);
    }
}
