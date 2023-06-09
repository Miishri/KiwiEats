package org.delivery.kiwieats.entities.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.delivery.kiwieats.entities.Order;
import org.delivery.kiwieats.entities.seller.Seller;
import org.delivery.kiwieats.model.product.ProductType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
