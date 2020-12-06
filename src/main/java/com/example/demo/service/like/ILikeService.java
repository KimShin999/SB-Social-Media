package com.example.demo.service.like;

import com.example.demo.model.AppLike;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface ILikeService {
    Iterable<AppLike> findAll();
    AppLike save (AppLike like);
    AppLike remove (Long id);
    Optional<AppLike> findById(Long id);
    List<AppLike> findAllByPost(Post post);
    Iterable<AppLike> findAllByPostId(Long id);
    Optional<AppLike> findByPostAndUser(Post post, AppUser user);
    int countLikes(Long id);
}
