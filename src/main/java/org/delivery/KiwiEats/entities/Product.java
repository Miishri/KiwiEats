package org.delivery.KiwiEats.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Product {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  private String productName;

  private String productImage;

  private BigDecimal price;

  private String category;

  @CreationTimestamp private Timestamp creationDate;

  @UpdateTimestamp private Timestamp lastUpdatedDate;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "seller_id")
  @JsonBackReference
  private Seller seller;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;
}
