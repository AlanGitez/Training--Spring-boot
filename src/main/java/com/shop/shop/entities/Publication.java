package com.shop.shop.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private float price;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "sold_out", nullable = false)
    private boolean sold_out = false;

    @OneToMany(mappedBy = "publication")
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (user_id) references user (user_id)"
            )
    )
    private User user;
}
