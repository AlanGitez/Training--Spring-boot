package com.shop.shop.services.implementation;

import com.shop.shop.dto.ProductDTO;
import com.shop.shop.dto.responses.ProductResponse;
import com.shop.shop.entities.Product;
import com.shop.shop.exceptions.ResourceNotFoundException;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
//        List<Product> products = page.getContent();
//
//        return page.getContent().stream().map(product -> DTOMapper(product)).collect(Collectors.toList());
        ProductResponse response = new ProductResponse();
        response.setContent(page.getContent().stream().map(product -> DTOMapper(product)).collect(Collectors.toList()));
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }

    @Override
    public ProductDTO getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
        return DTOMapper(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = EntityMapper(productDTO);

        Product newProduct = productRepository.save(product);

        return DTOMapper(newProduct);
    }

    @Override
    public ProductDTO updateById(ProductDTO dto, int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));

        product.setName(dto.getName());



        return DTOMapper(productRepository.save(product));
    }

    @Override
    public void deleteById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));

        productRepository.delete(product);
    }

    private ProductDTO DTOMapper(Product product) {

        ProductDTO DTO = new ProductDTO();
        DTO.setId(product.getId());
        DTO.setName(product.getName());


        return DTO;
    }

    ;

    private Product EntityMapper(ProductDTO DTO) {

        Product product = new Product();
        product.setName(DTO.getName());


        return product;
    }

    ;

}
