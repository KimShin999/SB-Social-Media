package com.example.demo.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import com.example.demo.model.PostImage;
import com.example.demo.service.post.IPostService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin( "*" )
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary cloudinary = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);


//
//    @PostMapping("/postStatus")
//    public ResponseEntity<Post> postStatus(@RequestBody Post post){
//        post.setImages(listImgDemo);
//        return new ResponseEntity<>(postService.save(post),HttpStatus.OK);
//    }

    @PostMapping("/imgPost")
    public List<String> imgPost(@RequestParam("file") MultipartFile[] files){
        List<PostImage> listImgDemo= new ArrayList();
        for (MultipartFile img : files) {
            try {
                File postImg = Files.createTempFile("temp", img.getOriginalFilename()).toFile();
                img.transferTo(postImg);
                Map responseAV = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
                JSONObject jsonAV = new JSONObject(responseAV);
                String urlAV = jsonAV.getString("url");
                PostImage postImage = new PostImage();
                postImage.setImg(urlAV);
                listImgDemo.add(postImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




    @GetMapping("/")
    public ResponseEntity<Iterable<Post>> showAll(){
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }

}
