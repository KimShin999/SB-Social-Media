package com.example.demo.service.postImg;

import com.example.demo.model.PostImage;
import com.example.demo.repository.post.IPostImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostImgServiceImpl implements IPostImgService{
    @Autowired
    private IPostImgRepository iPostImgRepository;

    @Override
    public Iterable<PostImage> findAll() {
        return iPostImgRepository.findAll();
    }

    @Override
    public PostImage save(PostImage post) {
        return iPostImgRepository.save(post);
    }

    @Override
    public PostImage remove(Long id) {
        PostImage postImage = iPostImgRepository.findById(id).get();
        iPostImgRepository.deleteById(id);
        return postImage;
    }

    @Override
    public Optional<PostImage> findById(Long id) {
        return iPostImgRepository.findById(id);
    }
}
