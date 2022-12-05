package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

  public ResponseEntity<ResponseWrapper> getUsers(){



  }

  public ResponseEntity<ResponseWrapper> getUsersByUserName(){}

  public ResponseEntity<ResponseWrapper> createUser(){}

  public ResponseEntity<ResponseWrapper> updateUser(){}

  public ResponseEntity<ResponseWrapper> deleteUser(){}



}
