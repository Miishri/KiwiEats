package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @NotBlank
    private String firstName;

    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    private String password;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 12)
    private Integer phone;


    @NotNull
    @NotBlank
    private String street;

    private String careOf;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String city;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String country;

    @Size(max = 10)
    private Integer postCode;

    @CreationTimestamp
    private LocalDateTime registeredDate;

    @NotNull
    @NotBlank
    private Boolean verified;

}