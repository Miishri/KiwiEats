package org.delivery.kiwieats.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.entities.rider.Rider;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class OrderDTO {

    private Long id;
    @NotNull
    private String orderDetails;

    @Size(min = 1, max = 50)
    private Integer quantity;

    private Boolean active;

    private LocalDateTime orderedDate;

    private Set<Product> products;

    @NotNull
    private Customer customer;

    @NotNull
    private Rider rider;
}
