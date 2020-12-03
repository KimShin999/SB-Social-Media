package com.example.demo.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Gender;
import com.example.demo.model.Post;
import com.example.demo.service.gender.IGenderService;
import com.example.demo.service.user.IUserService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary cloudinary = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);

    @Autowired
    IUserService userService;
    @Autowired
    IGenderService genderService;

    @GetMapping("/content")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/updateAvatar/{id}")
    public ResponseEntity<AppUser> updateAvatar(@PathVariable Long id,@RequestParam("imageFile") MultipartFile imgAvatar){
        AppUser user = userService.findById(id).get();
        try {
            File postImg = Files.createTempFile("temp", imgAvatar.getOriginalFilename()).toFile();
            imgAvatar.transferTo(postImg);
            Map responseAV = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(responseAV);
            String urlAV = jsonAV.getString("url");
            user.setAvatar(urlAV);
            userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/getAllGender")
    public ResponseEntity<Iterable<Gender>> showAll(){
        return  new ResponseEntity<>(genderService.findAll(),HttpStatus.OK);
    }

    @PutMapping("/changeInfo/{id}")
    public ResponseEntity<AppUser> changeInfo(@RequestBody AppUser user,@PathVariable Long id){
        AppUser appUser = userService.findById(id).get();
        appUser.setFirstName(user.getFirstName());
        appUser.setLastName(user.getLastName());
        appUser.setPhoneNumber(user.getPhoneNumber());
        appUser.setEmail(user.getEmail());
        userService.save(appUser);
        return new ResponseEntity<>(appUser,HttpStatus.OK);
    }

    @PutMapping("/updateCover/{id}")
    public ResponseEntity<AppUser> updateCover(@PathVariable Long id,@RequestParam("imageFile") MultipartFile imgAvatar) {
        AppUser user = userService.findById(id).get();
        try {
            File postImg = Files.createTempFile("temp", imgAvatar.getOriginalFilename()).toFile();
            imgAvatar.transferTo(postImg);
            Map rspCover = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(rspCover);
            String urlAV = jsonAV.getString("url");
            user.setCover(urlAV);
            userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Iterable<AppUser>> getAllUser(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<Iterable<AppUser>> getAllUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.getAllByUsernameContaining(name),HttpStatus.OK);
    }
}
