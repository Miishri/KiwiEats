package org.delivery.kiwieats.model.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.delivery.kiwieats.entities.Order;
import org.delivery.kiwieats.entities.seller.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ProductDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String productName;
    @NotNull
    @NotBlank
    private String productDescription;
    @NotNull
    @NotBlank
    private String productImageLink;
    private ProductType productType;
    @Size(min = 1)
    private Integer productStock;
    @Size(min = 1)
    private BigDecimal price;
    private Order order;
    private Seller seller;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
