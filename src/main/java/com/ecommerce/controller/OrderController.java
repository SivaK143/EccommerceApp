package com.ecommerce.controller;

import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody @Valid OrderRequestDTO request){
        orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.OK).body("Order placed successfully");
    }

    @GetMapping("/My")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getMyOrders());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }
}
