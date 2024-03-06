package org.delivery.KiwiEats.entities.roles;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import lombok.*;
import org.delivery.KiwiEats.entities.Customer;
import org.delivery.KiwiEats.entities.Seller;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Length(max = 50)
  private String username;

  @NotNull
  @NotBlank(message = "firstName cannot be empty")
  @Length(max = 50)
  private String firstName;

  @Length(max = 40)
  private String middleName;

  @NotNull
  @NotBlank(message = "First name cannot be empty")
  @Length(max = 50)
  private String lastName;

  @Email(
      message = "Email is not valid",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotBlank(message = "Email cannot be empty")
  private String email;

  @NotBlank(message = "Password cannot be empty")
  @Size(min = 8, message = "Password does not have correct length")
  @NotNull
  private String password;

  @CreationTimestamp private Timestamp creationDate;

  @UpdateTimestamp private Timestamp lastUpdatedDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "seller_id", nullable = false)
  private Seller seller;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  private boolean tokenExpired;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Collection<Role> roles;
}
