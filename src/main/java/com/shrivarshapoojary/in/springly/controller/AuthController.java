package com.shrivarshapoojary.in.springly.controller;

import com.shrivarshapoojary.in.springly.dto.RegisterRequest;
import com.shrivarshapoojary.in.springly.models.User;
import com.shrivarshapoojary.in.springly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {


    private UserService userService;
    @PostMapping("/public/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){


        User user =new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");

        userService.registerUser(user);

        return ResponseEntity.ok("User registered succesfully");
    }
}
