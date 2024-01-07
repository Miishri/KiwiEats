package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
  private Long customerId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id", referencedColumnName = "customer_id")
  private User user;

  @OneToMany(mappedBy="customer")
  private List<Product> cart;
}
