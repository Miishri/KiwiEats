package org.delivery.KiwiEats.models;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.roles.User;

@Data
@Builder
public class SellerDTO {
  private Long id;

  private List<Product> productInStock;

  private BigDecimal earnings;

  private User user;
}
