package com.shop.shop.dto;

import com.shop.shop.entities.Product;
import com.shop.shop.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PublicationDTO {
    private int id;
    private String title;
    private String Description;
    private float price;
    private boolean active = true;
    private boolean sold_out = false;
    private List<Product> products;
    private User user;

}
