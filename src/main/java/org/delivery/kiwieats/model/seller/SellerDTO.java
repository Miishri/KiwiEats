package org.delivery.kiwieats.model.seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.entities.seller.SellerDetails;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class SellerDTO {

    private UUID id;
    private Integer totalCustomers;

    private SellerDetails sellerDetails;

    private Set<Product> products;

    private BigDecimal revenue;
    @NotNull
    @NotBlank
    private Boolean verified;
}
