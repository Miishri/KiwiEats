package org.delivery.KiwiEats.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table("Seller")
public class Seller {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false, name = "seller_id")
    private UUID id;


}
