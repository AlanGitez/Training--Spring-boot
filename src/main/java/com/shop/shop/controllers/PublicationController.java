package com.shop.shop.controllers;


import com.shop.shop.dto.PublicationDTO;
import com.shop.shop.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;



    @GetMapping
    private ResponseEntity getAll(){
        return null;
    }

    @GetMapping("/{id}")
    private ResponseEntity getById(@PathVariable(name = "id") int id){

        return null;
    }

    @GetMapping("/{userId}")
    private ResponseEntity getAllByUserId(@PathVariable(name = "userId") int userId){
        return null;
    }

    @PostMapping("/add/{userId}")
    private ResponseEntity addSingle(
            @RequestBody PublicationDTO body,
            @PathVariable(name = "userId") int userId
    ){
        var response = publicationService.addSingle(body, userId);
        var error = (Boolean) response.get("error");
        return new ResponseEntity<>(response, error ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity updateById(
            @PathVariable(name = "id") int id,
            @RequestBody PublicationDTO body
    ){

        return null;
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable(name = "id") int id){
        return null;
    }
}
