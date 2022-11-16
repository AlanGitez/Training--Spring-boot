package com.shop.shop.repository;

import com.shop.shop.entities.Publication;
import com.shop.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    @Query(value = "SELECT p FROM Publication p WHERE p.userId LIKE %?1%")
    List<Publication> findByUserId(User userId);
}
