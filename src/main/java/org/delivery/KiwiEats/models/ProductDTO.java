package org.delivery.KiwiEats.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

@Data
@Builder
public class ProductDTO {
  private Long id;

  @NotNull
  @NotBlank(message = "Product Name cannot be empty")
  @Length(max = 200)
  private String productName;

  @NotNull
  @URL(protocol = "https", message = "Image is not in the right format!")
  private String productImage;

  @NotBlank(message = "Category cannot be empty")
  private String category;

  @NotNull
  @NotBlank(message = "Price cannot be empty")
  @Size(min = 1, max = 1000)
  private Double price;

  private Timestamp creationDate;

  @NotNull private Timestamp lastUpdatedDate;
}
