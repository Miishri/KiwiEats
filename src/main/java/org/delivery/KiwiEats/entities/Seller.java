package org.delivery.KiwiEats.entities;

<<<<<<< HEAD

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productInStock;

    private BigDecimal earnings;
}
