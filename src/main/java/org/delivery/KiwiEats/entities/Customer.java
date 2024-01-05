package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  @Column(name = "customer_id")
  private UUID customerId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id", referencedColumnName = "customer_id")
  private User user;

  @OneToMany(mappedBy="customer")
  private List<Product> cart;
}
