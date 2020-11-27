package com.example.demo.controller;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/content")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "User Content.";
    }

}
