package com.example.demo.controller;

import com.example.demo.model.AppLike;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import com.example.demo.repository.post.IPostRepository;
import com.example.demo.service.like.ILikeService;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private ILikeService likeService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<Iterable<AppLike>> showAllLike(){
        return new ResponseEntity<>(likeService.findAll(), HttpStatus.OK);
    }

    public Optional<AppLike> findByPostAndUser(Long postId,Long userId){
        Post post = postService.findById(postId).get();
        AppUser user = userService.findById(userId).get();
        return likeService.findByPostAndUser(post, user);
    }

    @GetMapping("/checkLike/{postId}/{userId}")
    public ResponseEntity<Boolean> isLike(@PathVariable Long postId, @PathVariable Long userId){
        if(findByPostAndUser(postId, userId).isPresent()){
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else
            return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @GetMapping("/update/{postId}/{userId}")
    public ResponseEntity<AppLike> deleteLike(@PathVariable Long postId, @PathVariable Long userId){
        if(findByPostAndUser(postId, userId).isPresent()){
            AppLike newLike = new AppLike();
            newLike = findByPostAndUser(postId, userId).get();
            return new ResponseEntity<>(likeService.remove(newLike.getId()), HttpStatus.OK);
        } else {
            AppLike newLike = new AppLike();
            newLike.setCreateAt(new Timestamp(System.currentTimeMillis()));
            newLike.setPost(postService.findById(postId).get());
            newLike.setUser(userService.findById(userId).get());
            postService.findById(postId).get().getLikes().add(newLike);
            return new ResponseEntity<>(likeService.save(newLike), HttpStatus.OK);
        }
    }

    @GetMapping("/countLikes/{id}")
    public ResponseEntity<Integer> countLikes(@PathVariable Long id){
        return new ResponseEntity<>(likeService.countLikes(id), HttpStatus.OK);
    }
}
