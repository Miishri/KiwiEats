package org.delivery.kiwieats.entities.customer;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.order.Order;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

}
