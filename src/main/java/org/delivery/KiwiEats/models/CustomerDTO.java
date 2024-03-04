package org.delivery.KiwiEats.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.roles.User;

@Data
@Builder
public class CustomerDTO {
  private Long customerId;

  @NotNull private User user;

  @NotNull @NotBlank private List<Product> cart;
}
