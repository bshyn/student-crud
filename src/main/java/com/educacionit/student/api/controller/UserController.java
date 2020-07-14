package com.educacionit.student.api.controller;

import com.educacionit.student.api.entity.RoleEntity;
import com.educacionit.student.api.entity.UserEntity;
import com.educacionit.student.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService<UserEntity> userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);

        userService.addUserRole(user.getId());

        if(user.getUsername().equalsIgnoreCase("admin")){
            userService.addAdminRole(user.getId());
        }

        return ResponseEntity.ok().build();
    }

}
