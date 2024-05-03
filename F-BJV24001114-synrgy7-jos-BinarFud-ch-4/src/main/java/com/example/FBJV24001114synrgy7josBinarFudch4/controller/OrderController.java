package com.example.FBJV24001114synrgy7josBinarFudch4.controller;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.OrderRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.OrderResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.projection.OrderDetailProjection;
import com.example.FBJV24001114synrgy7josBinarFudch4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(
            path = "/api/orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto request) {
        return orderService.save(request);
    }

    @GetMapping(
            path = "/api/orders/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<OrderDetailProjection> showOrderByUserId(@PathVariable("userId") UUID userId) {
        return orderService.getOrderDetailsByUserId(userId);
    }
}
