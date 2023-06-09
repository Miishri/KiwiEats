package org.delivery.kiwieats.model.rider;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.delivery.kiwieats.entities.Order;
import org.delivery.kiwieats.entities.rider.RiderDetails;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;


@Builder
@Data
public class RiderDTO {

    private UUID id;
    private RiderDetails details;
    private Set<Order> orders;
    @Size(min = 1)
    private BigDecimal tips;
    private BigDecimal totalPaid;
}
