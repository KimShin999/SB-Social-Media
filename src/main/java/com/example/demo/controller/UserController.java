package com.example.demo.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Relationship;
import com.example.demo.service.relationship.IRelationshipService;
import com.example.demo.service.status.IStatusService;
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
    IRelationshipService relationshipService;

    @Autowired
    IStatusService statusService;

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

    @PostMapping("/friendRequest")
    public ResponseEntity<Relationship> friendRequest(@RequestBody AppUser[] appUser){
        Relationship relationship = new Relationship();
        relationship.setFirstUser(appUser[0]);
        relationship.setSecondUser(appUser[1]);
        relationship.setStatus(statusService.findById(1L).get());
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @PutMapping("/friendResponse/{id}")
    public ResponseEntity<Relationship> agree (@PathVariable Long id){
        Relationship relationship = relationshipService.findById(id).get();
        relationship.setStatus(statusService.findById(2L).get());
        return new ResponseEntity<>(relationshipService.save(relationship), HttpStatus.OK);
    }

    @DeleteMapping("/unFriend/{id}")
    public ResponseEntity<Relationship>  unFriend(@PathVariable Long id){
        return  new ResponseEntity<>(relationshipService.remove(id), HttpStatus.OK);
    }

    @GetMapping("/relationship/{id}")
    public ResponseEntity<Relationship>  findRelationshipbyId(@PathVariable Long id){
        return new ResponseEntity<>(relationshipService.findById(id).get(), HttpStatus.OK);
    }

}
