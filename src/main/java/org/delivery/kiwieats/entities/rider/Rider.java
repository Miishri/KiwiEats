package org.delivery.kiwieats.entities.rider;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.delivery.kiwieats.entities.Order;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "rider_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "rider", cascade = CascadeType.ALL)
    private RiderDetails details;

    @OneToMany(mappedBy = "rider")
    private Set<Order> orders;

    @Size(min = 1)
    private BigDecimal tips;

    private BigDecimal totalPaid;
}
