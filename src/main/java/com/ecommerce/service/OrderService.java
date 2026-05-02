package com.ecommerce.service;

import com.ecommerce.dto.OrderItemRequestDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void placeOrder(OrderRequestDTO request){
        //1. Get logged in username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username :"+ username));

        //2. Create Order

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount=0;

        //3. Loop through items get from request
        for(OrderItemRequestDTO itemDTO : request.getItems()){

            //finding product through product Id
            Product product = productRepository.findById(itemDTO.getProductId()).orElseThrow(()-> new ResourceNotFoundException("product not found with product Id: "+ itemDTO.getProductId()));

            //4. Wrap it into OrderItem --> Create OrderItem
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());

            item.setPrice(product.getPrice());
            //5. set order relation
            item.setOrder(order);
            //6. Calculate total
            totalAmount += product.getPrice() * itemDTO.getQuantity();

            //adding itto ArrayList
            orderItems.add(item);
        }

        //7. Set items + total Amount
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        //save the order
        orderRepository.save(order);

    }

}
