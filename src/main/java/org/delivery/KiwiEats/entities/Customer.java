package org.delivery.KiwiEats.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.delivery.KiwiEats.entities.roles.User;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long customerId;

  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
  private User user;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Product> cart;
}
