package com.shop.shop.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private float price;

}
