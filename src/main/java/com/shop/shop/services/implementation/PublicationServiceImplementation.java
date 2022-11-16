package com.shop.shop.services.implementation;

import com.shop.shop.dto.PublicationDTO;
import com.shop.shop.entities.Product;
import com.shop.shop.entities.Publication;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.repository.PublicationRepository;
import com.shop.shop.repository.UserRepository;
import com.shop.shop.services.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImplementation extends BaseImplementation implements PublicationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String, Object> addSingle(PublicationDTO body, int userId) {

        Publication publication = modelMapper.map(body, Publication.class);
        Publication newPublication;
        try{
            var user = userRepository.findById(userId);

            if (user == null) return generateResponse(true,
                    String.format("User by id: %s not found", userId));

            publication.setUserId(user.get());

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

        var response = modelMapper.map(newPublication, PublicationDTO.class);

        return generateResponse(false, response);
    }

    @Override
    public Map<String, Object> getAll() {
        List<Publication> publications;
        try{
            publications = publicationRepository.findAll();
            if(publications.isEmpty() || publications == null)
                return generateResponse(true, "Publications not found");

        }catch(RuntimeException e){
            return generateResponse(true, e.getMessage());
        }
        var response = publications.stream().map(publication -> modelMapper.map(publication, PublicationDTO.class)).collect(Collectors.toList());
        return generateResponse(false, response);
    }
    @Override
    public Map<String, Object> getAllByUserId(int userId) {
//    return null;
        List<Publication> publications;
        try{
            var user = userRepository.findById(userId).get();
            publications = publicationRepository.findByUserId(user);
            System.out.println("publications");
            System.out.println(publications);
            if(publications.isEmpty() || publications == null)
                return  generateResponse(true, "Cannot find publication with id: "+ userId);

        } catch (RuntimeException e){
            return generateResponse(true, e.getMessage());
        }
        var response = publications.stream().map(item -> DTOMapper(item)).collect(Collectors.toList());
        return generateResponse(false, response);
    }

    @Override
    public Map<String, Object> getById(int id) {
        Publication publication;
        try{
            publication = publicationRepository.findById(id).get();
            if(publication == null) return  generateResponse(true, "Cannot find publication with id: "+ id);

        } catch (RuntimeException e){
            return generateResponse(true, e.getMessage());
        }
        return generateResponse(false, modelMapper.map(publication, PublicationDTO.class));
    }

    @Override
    public Map<String, Object> updateById(int id) {
        return null;
    }

    @Override
    public Map<String, Object> deleteById(int id) {
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
