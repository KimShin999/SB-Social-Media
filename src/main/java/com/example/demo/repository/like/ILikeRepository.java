package com.example.demo.repository.like;

import com.example.demo.model.AppLike;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ILikeRepository extends JpaRepository<AppLike, Long> {
    List<AppLike> findAllByPost(Post post);
    Optional<AppLike> findByPostAndUser(Post post, AppUser user);
    int countAllByPost(Post post);
}
