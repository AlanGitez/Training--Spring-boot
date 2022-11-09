package com.shop.shop.services;

import com.shop.shop.dto.ProductDTO;
import com.shop.shop.dto.responses.ProductResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    public ProductResponse getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
    public ProductDTO getById(int id);
    public ProductDTO createProduct(ProductDTO productDTO);

    public ProductDTO updateById(ProductDTO dto , int id );
    public void deleteById(int id);

}
