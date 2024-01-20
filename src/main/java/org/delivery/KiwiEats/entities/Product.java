package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Product {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String productName;

  private String productImage;

  @Enumerated(EnumType.STRING)
  private Category category;

  @CreationTimestamp
  private Timestamp creationDate;

  @UpdateTimestamp
  private Timestamp lastUpdatedDate;

  @ManyToOne
  @JoinColumn(name = "seller_id", nullable = false)
  private Seller seller;
}
