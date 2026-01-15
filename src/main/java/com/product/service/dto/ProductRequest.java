package com.product.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name must not exceed 100 characters")
  private String name;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.01", message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price must have max 2 decimals")
  private BigDecimal price;

  @Size(max = 100, message = "Description must not exceed 255 characters")
  private String description;

  @NotBlank(message = "Category is required")
  private String category;
}
/*
*Builder --> para que la creacion de objetos se vea mas limpia
* Antes
* ProductRequest request = new ProductRequest();
        request.setName("Bomba de baño");
        request.setPrice(BigDecimal.valueOf(160));
        request.setCategory("Higiene");
*
*
* */