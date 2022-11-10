package com.shop.shop.dto;


import com.shop.shop.entities.Publication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Publication> publications;
}
