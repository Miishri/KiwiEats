package org.delivery.KiwiEats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email_id")
  private String emailId;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy="customer")
  private List<Product> cart;

  @CreationTimestamp
  @Column(name = "customer_creation_date")
  private Timestamp creationDate;

  @UpdateTimestamp
  @Column(name = "last_updated_date")
  private Timestamp lastUpdatedDate;
}
