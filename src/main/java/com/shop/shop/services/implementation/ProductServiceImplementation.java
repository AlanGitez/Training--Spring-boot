package com.shop.shop.services.implementation;

import com.shop.shop.dto.ProductDTO;
import com.shop.shop.dto.responses.ProductResponse;
import com.shop.shop.entities.Product;
import com.shop.shop.exceptions.ResourceNotFoundException;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation extends BaseImplementation implements ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String, Object> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);

        ProductResponse response = new ProductResponse();
        response.setContent(page.getContent().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()));
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        return generateResponse(false, response);
    }

    @Override
    public Map<String, Object> getById(int id) {

        Optional<Product> product;
        try {
            product = productRepository.findById(id);
            if(product == null) {
                return generateResponse(true, String.format("Cannot find product by id: %s", id));
            }

        }catch (RuntimeException e){
            return generateResponse(true, e.getMessage());
        }

        return generateResponse(false, modelMapper.map(product, ProductDTO.class));

    }



    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {


        Product product = modelMapper.map(productDTO, Product.class);

        Product newProduct = productRepository.save(product);

        return modelMapper.map(newProduct, ProductDTO.class);
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

    private Product EntityMapper(ProductDTO DTO) {

        Product product = new Product();
        product.setName(DTO.getName());


        return product;
    }

}
