package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Product name cannot be empty")
    private String name;
    @Positive(message = "Price must be greater than zero")
    private double price;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @NotNull(message = "CategoryId is required")
    private Long categoryId;
}
