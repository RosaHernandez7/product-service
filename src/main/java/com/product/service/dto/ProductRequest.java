package com.product.service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
