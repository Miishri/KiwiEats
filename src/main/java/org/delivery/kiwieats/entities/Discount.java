package org.delivery.kiwieats.entities;


import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.model.ProductType;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private ProductType productType;

    private BigDecimal discountPercentage;

    private Boolean valid;

}
