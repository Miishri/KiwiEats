package org.delivery.KiwiEats.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.User;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID customerId;

    @NotNull
    private User user;

    @NotNull
    @NotBlank
    private List<Product> cart;
}
