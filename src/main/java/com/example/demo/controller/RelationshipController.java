package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Relationship;
import com.example.demo.service.relationship.IRelationshipService;
import com.example.demo.service.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/relationships")
public class RelationshipController {

    @Autowired
    IRelationshipService relationshipService;

    @Autowired
    IStatusService statusService;

    @PostMapping("/friendRequest")
    public ResponseEntity<Relationship> friendRequest(@RequestBody AppUser[] appUser){
        Relationship relationship = new Relationship();
        relationship.setFirstUser(appUser[0]);
        relationship.setSecondUser(appUser[1]);
        relationship.setStatus(statusService.findById(1L).get());                   //check optional
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @PutMapping("/friendResponse/{id}")
    public ResponseEntity<Relationship> agree (@PathVariable Long id){
        Relationship relationship = relationshipService.findById(id).get();         //check optional
        relationship.setStatus(statusService.findById(2L).get());                   //check optional
        return new ResponseEntity<>(relationshipService.save(relationship), HttpStatus.OK);
    }

    @DeleteMapping("/unFriend/{id}")
    public ResponseEntity<Relationship>  unFriend(@PathVariable Long id){
        return  new ResponseEntity<>(relationshipService.remove(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relationship>  findRelationshipById(@PathVariable Long id){
        return new ResponseEntity<>(relationshipService.findById(id).get(), HttpStatus.OK);         //check optional
    }
}
