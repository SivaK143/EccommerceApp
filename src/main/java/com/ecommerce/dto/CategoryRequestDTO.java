package com.ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CategoryRequestDTO {

    @NotBlank(message = "Category name cannot be empty")
    private String name;
}
