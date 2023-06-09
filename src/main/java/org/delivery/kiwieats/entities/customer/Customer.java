package org.delivery.kiwieats.entities.customer;

import jakarta.persistence.*;
import lombok.*;
import org.delivery.kiwieats.entities.Discount;
import org.delivery.kiwieats.entities.Order;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "customer_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerDetails details;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer")
    private Set<Discount> discounts;

}