package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seller_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productInStock;

    private BigDecimal earnings;
}
