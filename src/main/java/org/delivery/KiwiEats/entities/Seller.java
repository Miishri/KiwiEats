package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Seller {
    @Id
    @UuidGenerator
    @Column(name = "seller_id")
    private UUID id;

    @OneToOne(mappedBy = "user")
    private User user;

    @OneToMany
    private List<Product> productInStock;

    private BigDecimal earnings;
}
