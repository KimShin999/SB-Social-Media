package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import com.example.demo.model.PostImage;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.postImg.IPostImgService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IPostImgService postImgService;

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

    @GetMapping("/createpost/{content}")
    public ResponseEntity<Post> Post(@PathVariable String content){
        Post post = new Post();
        post.setContent(content);
        post.setImages(listImgDemo);
        listImgDemo = new ArrayList<>();
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Post>> showAll(){
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }

}
