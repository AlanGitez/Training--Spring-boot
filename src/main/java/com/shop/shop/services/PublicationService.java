package com.shop.shop.services;

import com.shop.shop.dto.PublicationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

public interface PublicationService {

    public Map<String, Object> addSingle(PublicationDTO body, int userId);
    // only available for admins.
    // AdminController file is required for some routes.
    public Map<String, Object> getAll();
    public Map<String, Object> getAllByUserId(int id);
    public Map<String, Object> getById(int id);
    public Map<String, Object> updateById(int id);
    public Map<String, Object> deleteById(int id);

}
