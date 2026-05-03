package com.ecommerce.mapper;

import com.ecommerce.dto.OrderItemResponseDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.entity.Order;

import java.util.List;

public class OrderMapper {

    public static OrderResponseDTO mapToDTO(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());

        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream()
                .map(item ->{
                            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();
                            itemDTO.setProductName(item.getProduct().getName());
                            itemDTO.setQuantity(item.getQuantity());
                            itemDTO.setPrice(item.getPrice());
                            return itemDTO;
                        })
                .toList();

        dto.setItems(itemDTOs);
        return dto;
    }
}
