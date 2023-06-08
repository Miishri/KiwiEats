package org.delivery.kiwieats.entities.rider;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.delivery.kiwieats.entities.Address;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rider_details")
public class RiderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @NotNull
    @NotBlank
    private String firstName;

    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    //implement password later
    //private Password password;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 12)
    private Integer phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime registeredDate;

    @NotNull
    @NotBlank
    private Boolean verified;
    @PrePersist
    private void prePersist() {
        verified = false;
    }
}
