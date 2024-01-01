package org.delivery.KiwiEats.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.KiwiEats.entities.Category;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull
    private Long id;

    @NotNull
    @NotBlank(message = "Product Name cannot be empty")
    @Length(max=200)
    private String productName;

    @NotNull
    @URL(protocol = "https",message = "Image is not in the right format!")
    private String productImage;

    @NotNull
    @NotBlank(message = "Category cannot be empty")
    private Category category;

    @NotNull
    @NotBlank(message = "Price cannot be empty")
    @Size(min = 1, max = 1000)
    private Double price;

    @NotNull
    private Timestamp creationDate;

    @NotNull
    private Timestamp lastUpdatedDate;
}
