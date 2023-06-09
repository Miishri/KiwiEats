package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.entities.rider.Rider;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String orderDetails;

    @Size(min = 1, max = 50)
    private Integer quantity;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime orderedDate;

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

}
