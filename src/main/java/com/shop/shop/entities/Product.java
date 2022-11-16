package com.shop.shop.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

//    @ManyToMany
//    @JoinTable(
//            name = "products_categories",
//            joinColumns = @JoinColumn(
//                    name = "product_id",
//                    nullable = true,
//                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (product_id) references products (id)")
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "category_id",
//                    nullable = false,
//                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (category_id) references categories (id)")
//            )
//    )
//    private List<Categories> categories;

//    @JsonManagedReference
    @ManyToOne
    private Publication publication;

}
