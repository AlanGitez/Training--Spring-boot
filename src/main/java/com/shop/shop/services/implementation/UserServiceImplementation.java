package com.shop.shop.services.implementation;

import com.shop.shop.dto.UserDTO;
import com.shop.shop.entities.User;
import com.shop.shop.repository.UserRepository;
import com.shop.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

@Service
public class UserServiceImplementation extends BaseImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> addSingle(UserDTO body) {
        User newUser;
        try {
            User user = EntityMapper(body);
            user.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser = userRepository.save(user);
            if(newUser == null) return generateResponse(true, "Cannot create user with data provided");

        }catch (RuntimeException e){
            return generateResponse(true, e.getMessage());
        }
        return generateResponse(false, DTOMapper(newUser));
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


    private UserDTO DTOMapper(User user) {

        UserDTO DTO = new UserDTO();
        DTO.setId(user.getId());
        DTO.setName(user.getName());
        DTO.setEmail(user.getEmail());
        DTO.setPassword(user.getPassword());
//        DTO.setPublications();


        return DTO;
    }

    private User EntityMapper(UserDTO DTO) {

        User user = new User();
        user.setName(DTO.getName());
        user.setEmail(DTO.getEmail());
        user.setPassword(DTO.getPassword());
//        user.setPublications();


        return user;
    }
}
