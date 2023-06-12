package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "riders")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "rider")
    private Set<Order> orders;

    private BigDecimal tips;

    private BigDecimal totalPaid;

    private Boolean verified;
}
