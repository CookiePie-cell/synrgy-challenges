package com.example.FBJV24001114synrgy7josBinarFudch5.controller;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.ApiResponse;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.projection.OrderDetailProjection;
import com.example.FBJV24001114synrgy7josBinarFudch5.service.InvoiceService;
import com.example.FBJV24001114synrgy7josBinarFudch5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    private final InvoiceService invoiceService;

    @Autowired
    public OrderController(OrderService orderService, InvoiceService invoiceService) {
        this.orderService = orderService;
        this.invoiceService = invoiceService;
    }

    @PostMapping(
            path = "/api/orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<OrderResponseDto>> placeOrder(@RequestBody OrderRequestDto request) {
        OrderResponseDto response = orderService.save(request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<OrderResponseDto>builder()
                        .statusCode(201)
                        .data(response)
                        .build());
    }

    @GetMapping(
            path = "/api/orders/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<OrderDetailProjection>>> showOrderByUserId(@PathVariable("userId") UUID userId) {
        List<OrderDetailProjection> response =  orderService.getOrderDetailsByUserId(userId);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<List<OrderDetailProjection>>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }

    @PostMapping(
            path = "/api/orders/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<String>> generateInvoice(@PathVariable("userId") UUID userId) {
        invoiceService.generateInvoice(userId);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<String>builder()
                        .statusCode(200)
                        .data("Success")
                        .build());
    }
}
