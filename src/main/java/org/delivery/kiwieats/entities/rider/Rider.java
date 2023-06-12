package org.delivery.kiwieats.entities.rider;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.order.Order;

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
