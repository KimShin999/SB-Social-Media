package com.example.demo.service.privacypost;

import com.example.demo.model.PrivacyPost;

import java.util.Optional;

public interface IPrivacyPostService {
    Optional<PrivacyPost> findById(Long id);
}
