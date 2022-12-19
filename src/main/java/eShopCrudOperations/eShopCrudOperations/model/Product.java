package eShopCrudOperations.eShopCrudOperations.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "tbl_product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="product_id")
    private int id;

    @Column(name="product_name")
    private String name;

    @Column(name="product_rate")
    private double rate;

    @Column(name="product_type")
    private String type;



}
