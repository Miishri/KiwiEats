package org.delivery.KiwiEats.models;


import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.User;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SellerDTO {
    private UUID id;

    private List<Product> productInStock;

    private BigDecimal earnings;

    private User user;
}

