package org.delivery.KiwiEats.models;


import lombok.Builder;
import lombok.Data;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.User;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SellerDTO {
    private Long id;

    private List<Product> productInStock;

    private BigDecimal earnings;

    private User user;
}

