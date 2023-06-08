package org.delivery.kiwieats.entities;


import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.product.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ProductType productType;

    private BigDecimal discountPercentage;

    private Boolean valid;

    @PrePersist
    private void prePersist() {
        valid = true;
    }
}
