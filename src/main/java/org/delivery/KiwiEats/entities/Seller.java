package org.delivery.KiwiEats.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.delivery.KiwiEats.entities.roles.User;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Seller {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "seller_id")
  private Long id;

  @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Product> productInStock;

  private BigDecimal earnings;
}
