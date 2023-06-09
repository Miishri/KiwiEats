package org.delivery.kiwieats.entities.seller;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.delivery.kiwieats.entities.product.Product;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Seller {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "seller_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    private Integer totalCustomers;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private SellerDetails sellerDetails;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    private BigDecimal revenue;

    @NotNull
    @NotBlank
    private Boolean verified;

}
