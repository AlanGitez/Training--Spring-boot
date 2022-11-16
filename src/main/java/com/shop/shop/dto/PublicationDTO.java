package com.shop.shop.dto;

import com.shop.shop.entities.Product;
import com.shop.shop.entities.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDTO {
    private int id;
    private String title;
    private String Description;
    private float price;
    private boolean active = true;
    private boolean sold_out = false;
    private List<Product> products;
    private User userId;


    public User getUserId(){
        return this.userId;
    }

    public void setUserId(User user){
        this.userId = user;
    }

}
