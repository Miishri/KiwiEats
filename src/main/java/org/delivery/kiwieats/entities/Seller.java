package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    @NotBlank
    private Boolean verified;

}
