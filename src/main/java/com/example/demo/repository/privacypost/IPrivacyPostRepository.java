package com.example.demo.repository.privacypost;

import com.example.demo.model.PrivacyPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivacyPostRepository extends JpaRepository<PrivacyPost, Long> {
}
