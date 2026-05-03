package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter@Setter
public class OrderResponseDTO {

    private Long orderId;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDTO> items;
}
