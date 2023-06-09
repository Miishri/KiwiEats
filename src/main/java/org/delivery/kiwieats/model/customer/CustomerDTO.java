package org.delivery.kiwieats.model.customer;

import lombok.Builder;
import lombok.Data;
import org.delivery.kiwieats.entities.Discount;
import org.delivery.kiwieats.entities.Order;
import org.delivery.kiwieats.entities.customer.CustomerDetails;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class CustomerDTO {
    private UUID id;
    private CustomerDetails details;
    private Set<Order> orders;
    private Set<Discount> discounts;
}
