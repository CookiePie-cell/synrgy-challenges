package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.projection.OrderDetailProjection;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDto save(OrderRequestDto request);

    List<OrderDetailProjection> getOrderDetailsByUserId(UUID userId);

}
