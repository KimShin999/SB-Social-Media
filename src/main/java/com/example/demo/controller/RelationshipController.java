package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Relationship;
import com.example.demo.service.relationship.IRelationshipService;
import com.example.demo.service.status.IStatusService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/relationships")
public class RelationshipController {

    @Autowired
    IRelationshipService relationshipService;

    @Autowired
    IStatusService statusService;

    @Autowired
    IUserService userService;

    @PostMapping("/friendRequest")
    public ResponseEntity<Relationship> friendRequest(@RequestBody AppUser[] appUser){
        Relationship relationship = new Relationship();
        relationship.setFirstUser(appUser[0]);
        relationship.setSecondUser(appUser[1]);
        relationship.setStatus(statusService.findById(1L).get());                   //check optional
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @GetMapping("/makeFriendRequest/{id1}/{id2}")
    public ResponseEntity<Relationship> makeFriendRequest(@PathVariable Long id1, @PathVariable Long id2){
        Relationship relationship = new Relationship();
        relationship.setFirstUser(userService.findById(id1).get());
        relationship.setSecondUser(userService.findById(id2).get());
        relationship.setStatus(statusService.findById(1L).get());                   //check optional
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @GetMapping("/friendResponse/{id}")
    public ResponseEntity<Relationship> agree (@PathVariable Long id){
        Relationship relationship = relationshipService.findById(id).get();         //check optional
        relationship.setStatus(statusService.findById(2L).get());                   //check optional
        return new ResponseEntity<>(relationshipService.save(relationship), HttpStatus.OK);
    }

    @DeleteMapping("/unFriend/{id}")
    public ResponseEntity<Relationship>  unFriend(@PathVariable Long id){
        return  new ResponseEntity<>(relationshipService.remove(id), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Relationship>  findRelationshipById(@PathVariable Long id){
//        return new ResponseEntity<>(relationshipService.findById(id).get(), HttpStatus.OK);         //check optional
//    }

    @GetMapping("/getRelationship/{id1}/{id2}")
    public ResponseEntity<Relationship>  findRelationshipByIdAndId(@PathVariable Long id1, @PathVariable Long id2) {
        if (relationshipService.findByFirstUserIdAndSecondUserId(id1, id2).isPresent()) {
            return new ResponseEntity<>(relationshipService.findByFirstUserIdAndSecondUserId(id1, id2).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(relationshipService.findByFirstUserIdAndSecondUserId(id2, id1).get(), HttpStatus.OK);

        }
    }

    @PostMapping("/getListRelationship/{id}")
    public ResponseEntity<List<Relationship>>  findListRelationshipByIdAndId(@PathVariable Long id, @RequestBody AppUser[] appUser) {
        List<Relationship> listRelationship = new ArrayList<>();
        for (AppUser user: appUser) {
            if (relationshipService.findByFirstUserIdAndSecondUserId(user.getId(), id).isPresent()) {
                listRelationship.add(relationshipService.findByFirstUserIdAndSecondUserId(user.getId(), id).get());
            }else if (relationshipService.findByFirstUserIdAndSecondUserId(id, user.getId()).isPresent()){
                listRelationship.add(relationshipService.findByFirstUserIdAndSecondUserId(id, user.getId()).get());
            }
        }
        return new ResponseEntity<>(listRelationship, HttpStatus.OK);
    }


    @GetMapping("/checkRelationship/{id1}/{id2}")
    public ResponseEntity<Optional<Relationship>>  checkRelationshipByIdAndId(@PathVariable Long id1, @PathVariable Long id2){
        return new ResponseEntity<>(relationshipService.findByFirstUserIdAndSecondUserId(id1, id2), HttpStatus.OK);
    }

    @GetMapping("/getListFriend/{id1}")
    public ResponseEntity<Iterable<AppUser>> findFriend(@PathVariable Long id1){
        List<Relationship> listRelationship = (List<Relationship>) relationshipService.getAllByFirstUserIdOrSecondUserId(id1,id1);
        List<AppUser> listFriendRelationship = new ArrayList<>();
        for ( Relationship relationship:  listRelationship) {
            if (relationship.getStatus().getId() == 2){
                if (relationship.getFirstUser().getId() != id1){
                    if (relationship.getStatus().getId() == 2){
                        listFriendRelationship.add(relationship.getFirstUser());
                    }
                }
                if (relationship.getSecondUser().getId() != id1){
                    if (relationship.getStatus().getId() == 2){
                        listFriendRelationship.add(relationship.getSecondUser());
                    }
                }
            }
        }
        return new ResponseEntity<>(listFriendRelationship,HttpStatus.OK);
    }

    @GetMapping("/getListFriendRequest/{id}")
    public ResponseEntity<Iterable<Relationship>> findFriendRequest(@PathVariable Long id){
        List<Relationship> listRelationship = (List<Relationship>) relationshipService.getAllBySecondUserId(id);
        List<Relationship> listFriendRequest = new ArrayList<>();
        for ( Relationship relationship:  listRelationship) {
            if (relationship.getStatus().getId() == 1){
                listFriendRequest.add(relationship);
            }
        }
        return new ResponseEntity<>(listFriendRequest,HttpStatus.OK);
    }
}
