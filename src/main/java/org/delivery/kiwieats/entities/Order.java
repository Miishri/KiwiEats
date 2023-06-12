package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "order_details")
    private String orderDetails;

    @Column(name = "quantity")
    private Integer quantity;

    private Boolean active;

    @CreationTimestamp
    private LocalDateTime orderedDate;

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private Rider rider;

}
