package com.shop.shop.services;

import com.shop.shop.dto.UserDTO;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

public interface UserService {

    public Map<String, Object> addSingle(UserDTO body);
    // only available for admins.
    // AdminController file is required for some routes.
    public Map<String, Object> getAll();
    public Map<String, Object> getAllByUserId();
    public Map<String, Object> getById();
    public Map<String, Object> updateById();
    public Map<String, Object> deleteById();
}
