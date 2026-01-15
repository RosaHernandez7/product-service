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
public class ProductUpdate {
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have max 2 decimals")
    private BigDecimal price;

    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    private String category;

    @PositiveOrZero(message = "Stock must be zero or positive")
    private Long stock;

    private Boolean active;
}
