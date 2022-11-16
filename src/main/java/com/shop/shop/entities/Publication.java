package com.shop.shop.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "publications")
public class Publication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private float price = 0.0f;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "sold_out", nullable = false)
    private boolean sold_out = false;

    @JsonBackReference
    @OneToMany(
            mappedBy = "publication",
            cascade = CascadeType.ALL,
            orphanRemoval = true
//            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

//    @JsonManagedReference
    @ManyToOne()
    private User userId;

    public User getUserId(){
        return this.userId;
    }

    public void setUserId(User user){
        this.userId = user;
    }

}
