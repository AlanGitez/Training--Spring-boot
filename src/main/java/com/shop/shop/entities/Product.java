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
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    nullable = true,
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (product_id) references products (product_id)")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    nullable = false,
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (category_id) references categories (category_id)")
            )
    )
    private List<Categories> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "publication_id",
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (publication_id) references publications (publication_id)"
            )
    )
    private Publication publication;


}
