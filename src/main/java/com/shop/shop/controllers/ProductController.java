package com.shop.shop.controllers;

import com.shop.shop.dto.ProductDTO;
import com.shop.shop.services.ProductService;
import com.shop.shop.utils.AppConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(value = "pageNumber", defaultValue = AppConstans.PAGE_NUMBER_DEFECT, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstans.PAGE_SIZE_DEFECT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstans.SORT_BY_DEFECT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstans.SORT_DIR_DEFECT, required = false) String sortDir
    ) {
        var response = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity(response, HttpStatus.OK);
    };


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable(name = "id") int id) {
        var response = productService.getById(id);
        return ResponseEntity.ok(response);
    };

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody
            ProductDTO product
    ) {

        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    };


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateById(@RequestBody ProductDTO dto, @PathVariable(name = "id") int id) {
        var response = productService.updateById(dto, id);
        return ResponseEntity.ok(response);
    };

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") int id) {
        productService.deleteById(id);
        return new ResponseEntity("Deleted succesfully", HttpStatus.OK);
    };
}
