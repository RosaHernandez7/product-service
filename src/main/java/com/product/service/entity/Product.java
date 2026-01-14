package com.product.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize =1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="product_seq")
    private Long idProduct;

    @Column(unique =true, nullable = false, length =100)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length =100)
    private String category;

    @Column(length =100)
    private String description;

    private Boolean active;

    private Long stock;
}
