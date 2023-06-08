package org.delivery.kiwieats.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long id;

    @NotNull
    @NotBlank
    private String street;

    @Column(name = "care_of")
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

}
