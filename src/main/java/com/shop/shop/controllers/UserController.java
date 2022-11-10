package com.shop.shop.controllers;


import com.shop.shop.dto.UserDTO;
import com.shop.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private ResponseEntity addSingle(@RequestBody UserDTO body){

        var response = userService.addSingle(body);
        var error = (Boolean) response.get("error");

        return new ResponseEntity(response, error ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }
}
