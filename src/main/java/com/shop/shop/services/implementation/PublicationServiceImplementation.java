package com.shop.shop.services.implementation;

import com.shop.shop.dto.PublicationDTO;
import com.shop.shop.entities.Product;
import com.shop.shop.entities.Publication;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.repository.PublicationRepository;
import com.shop.shop.repository.UserRepository;
import com.shop.shop.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PublicationServiceImplementation extends BaseImplementation implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String, Object> addSingle(PublicationDTO body, int userId) {
        Publication publication = EntityMapper(body);
        Publication newPublication;
        try{
            var user = userRepository.findById(userId);

            if (user == null) return generateResponse(true,
                    String.format("User by id: %s not found", userId));

            publication.setUser(user.get());

            var products = body.getProducts();

            if(products.isEmpty() || products == null) return generateResponse(true,
                    "Cannot create a publication without product 's list");

            products.forEach(product -> product.setPublication(publication));

            newPublication = publicationRepository.save(publication);
            if(newPublication == null) return generateResponse(true,
                    "Cannot create publication, bad request");

            List<Product> newProducts = productRepository.saveAll(products);
            if(newProducts == null) return generateResponse(true,
                    "Cannot create products, bad request");


        } catch (RuntimeException e){
            return generateResponse(true, e.getMessage());
        }

        return generateResponse(false, DTOMapper(newPublication));
    }

    @Override
    public Map<String, Object> getAll() {
        return null;
    }

    @Override
    public Map<String, Object> getAllByUserId() {
        return null;
    }

    @Override
    public Map<String, Object> getById() {
        return null;
    }

    @Override
    public Map<String, Object> updateById() {
        return null;
    }

    @Override
    public Map<String, Object> deleteById() {
        return null;
    }

    private PublicationDTO DTOMapper(Publication publication) {
        PublicationDTO DTO = new PublicationDTO();
        DTO.setId(publication.getId());
        DTO.setTitle(publication.getTitle());
        DTO.setDescription(publication.getDescription());
        DTO.setPrice(publication.getPrice());
        DTO.setActive(publication.isActive());
        DTO.setSold_out(publication.isSold_out());

        return DTO;
    }

    private Publication EntityMapper(PublicationDTO DTO) {
        Publication publication = new Publication();
        publication.setTitle(DTO.getTitle());
        publication.setDescription(DTO.getDescription());
        publication.setPrice(DTO.getPrice());
        publication.setActive(DTO.isActive());
        publication.setSold_out(DTO.isSold_out());

        return publication;
    }
}
