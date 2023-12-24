package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "product_image")
  private String productImage;

  @Enumerated(EnumType.STRING)
  @Column(name = "product_category")
  private Category category;

  @CreationTimestamp
  @Column(name = "product_creation_date")
  private Timestamp creationDate;

  @UpdateTimestamp
  @Column(name = "last_updated_date")
  private Timestamp lastUpdatedDate;
}
