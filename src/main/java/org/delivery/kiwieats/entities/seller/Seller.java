package org.delivery.kiwieats.entities.seller;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.product.Product;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer totalCustomers;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    private BigDecimal revenue;
    private Boolean verified;

}
