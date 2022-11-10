package com.shop.shop.entities;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    nullable = true,
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (product_id) references products (id)")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    nullable = false,
                    foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (category_id) references categories (id)")
            )
    )
    private List<Categories> categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "publication_id",
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "foreign key (publication_id) references publications (id)"
            )
    )
    private Publication publication;


}
