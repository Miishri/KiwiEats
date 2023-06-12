package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID userId;

    private String firstName;

    private String lastName;
    private String email;

    private String password;

    private Integer phone;

    private String street;

    private String careOf;

    private String city;

    private String country;

    private Integer postCode;

    @CreationTimestamp
    private LocalDateTime registeredDate;

    private Boolean verified;

}