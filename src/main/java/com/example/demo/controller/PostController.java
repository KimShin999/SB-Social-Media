package com.example.demo.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.PostImage;
import com.example.demo.service.post.ICommentService;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.postImg.IPostImgService;
import com.example.demo.service.privacypost.IPrivacyPostService;
import com.example.demo.service.user.IUserService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostImgService postImgService;
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPrivacyPostService privacyPostService;

    List<PostImage> listImgDemo= new ArrayList();
    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary cloudinary = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
    @PostMapping("/imgPost")
    public void imgPost(@RequestParam("file") MultipartFile[] files){
        for (MultipartFile img : files) {
            try {
                File postImg = Files.createTempFile("temp", img.getOriginalFilename()).toFile();
                img.transferTo(postImg);
                Map responseAV = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
                JSONObject jsonAV = new JSONObject(responseAV);
                String urlAV = jsonAV.getString("url");
                PostImage postImage = new PostImage();
                postImage.setImg(urlAV);
                postImgService.save(postImage);
                listImgDemo.add(postImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("/createpost/{id}")
    public ResponseEntity<Post> Post(@RequestBody Post post,@PathVariable Long id){
        AppUser user = userService.findById(id).get();
        post.setImages(listImgDemo);
        post.setAppUser(user);
        post.setCreateAt(new Timestamp(System.currentTimeMillis()));
        post.setPrivacyPost(privacyPostService.findById(1L).get());
        postService.save(post);
        listImgDemo = new ArrayList<>();
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<Post>> showAll(){
        Iterable<Post> listPost = postService.findAll();
        return new ResponseEntity<>(listPost,HttpStatus.OK);
    }

    @GetMapping("/getAllPostsByUser/{id}")
    public ResponseEntity<Iterable<Post>> getPost(@PathVariable Long id){
        AppUser user = userService.findById(id).get();
        List<Post> posts = postService.findAllByAppUser(user);
//        stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        Collections.reverse(posts);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @PostMapping("/postComment/{userId}/{id}")
    public ResponseEntity<Comment> postComment (@PathVariable Long id, @RequestBody Comment comment,@PathVariable Long userId){
        AppUser user = userService.findById(userId).get();
        Post post = postService.findById(id).get();
        commentService.save(comment);
        comment.setUser(user);
        comment.setPost(post);
        post.getComments().add(comment);
        postService.save(post);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }
    @GetMapping("getAllCommentsByPost/{id}")
    public ResponseEntity<Iterable<Comment>> getAllComment(@PathVariable Long id){
        Post post = postService.findById(id).get();
        Iterable<Comment> comments = commentService.findAllByPost(post);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updateComment(@RequestBody Post post,@PathVariable Long id ){
        if (post.getAppUser().getId()== id){
            Post post1 = postService.findById(post.getId()).get();
//            post.setImages(post1.getImages());
            postService.save(post);
            return new ResponseEntity<>(post,HttpStatus.OK);
        }
        return null;
    }
    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id){
        postService.remove(id);
    }

    @PutMapping("/updateComment/{idUser}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long idUser) {
        if (comment.getUser().getId() == idUser){
            commentService.save(comment);
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping ("/deleteComment/{idUser}/{idComment}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long idUser,@PathVariable Long idComment){
        Comment comment = commentService.findById(idComment).get();
        if (comment.getUser().getId() == idUser){
            return new ResponseEntity<>(commentService.remove(idComment),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}