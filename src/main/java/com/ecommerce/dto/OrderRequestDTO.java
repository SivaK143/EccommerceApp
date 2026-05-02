package com.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class OrderRequestDTO {

    @NotEmpty
    private List<OrderItemRequestDTO> items;
}
