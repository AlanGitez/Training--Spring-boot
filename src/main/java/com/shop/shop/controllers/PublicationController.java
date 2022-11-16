package com.shop.shop.controllers;


import com.shop.shop.dto.PublicationDTO;
import com.shop.shop.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;



    @GetMapping
    private ResponseEntity<?> getAll(){

        var response = publicationService.getAll();
        var error = (Boolean)response.get("error");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity getById(@PathVariable(name = "id") int id){
        var response = publicationService.getById(id);
        var error = (boolean) response.get("error");
        return new ResponseEntity(response, error ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity getAllByUserId(@PathVariable(name = "userId") int userId){

        var response = publicationService.getAllByUserId(userId);
        var error = (boolean) response.get("error");
        return new ResponseEntity(response, error ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

//    @PostMapping(value = "/add/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/add/{userId}")
    private ResponseEntity<?> addSingle(
            @RequestBody PublicationDTO body,
            @PathVariable(name = "userId") int userId
    ){
        var response = publicationService.addSingle(body, userId);
        var error = (Boolean) response.get("error");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity updateById(
            @PathVariable(name = "id") int id,
            @RequestBody PublicationDTO body
    ){

        return null;
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteById(@PathVariable(name = "id") int id){
        return null;
    }
}
