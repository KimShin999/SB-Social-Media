package com.example.demo.service.like;

import com.example.demo.model.AppLike;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import com.example.demo.repository.like.ILikeRepository;
import com.example.demo.repository.post.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ILikeServiceImpl implements ILikeService{

    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    IPostRepository postRepository;

    @Override
    public Iterable<AppLike> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public AppLike save(AppLike like) {
        return likeRepository.save(like);
    }

    @Override
    public AppLike remove(Long id) {
        AppLike like = likeRepository.findById(id).get();
        likeRepository.deleteById(id);
        return like;
    }

    @Override
    public Optional<AppLike> findById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public List<AppLike> findAllByPost(Post post) {
        return likeRepository.findAllByPost(post);
    }

    @Override
    public Optional<AppLike> findByPostAndUser(Post post, AppUser user) {
        return likeRepository.findByPostAndUser(post, user);
    }

    @Override
    public int countLikes(Long id) {
        return likeRepository.countAllByPost(postRepository.findById(id).get());
    }
}
