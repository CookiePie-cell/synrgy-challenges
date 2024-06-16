package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.OrderResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.Order;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.OrderDetail;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.Product;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.User;
import com.example.FBJV24001114synrgy7josBinarFudch5.projection.OrderDetailProjection;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.OrderRepository;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.ProductRepository;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public OrderResponseDto save(OrderRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Produk tidak ditemukan"));

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setOrderTime(Timestamp.from(Instant.now()));
        order.setDestinationAddress(request.getDestinationAddress());
        order.setCompleted(false);
        order.setUser(user);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(UUID.randomUUID());
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setTotalPrice(product.getPrice() * request.getQuantity());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);
        order.setOrderDetails(orderDetailList);

        return mapToOrderResponseDto(orderRepository.save(order), orderDetail);
    }

    private OrderResponseDto mapToOrderResponseDto(Order order, OrderDetail orderDetail) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .orderTime(order.getOrderTime())
                .destinationAddress(order.getDestinationAddress())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .build();
    }

    @Override
    public List<OrderDetailProjection> getOrderDetailsByUserId(UUID userId) {
        return orderRepository.getOrderDetailsByUserId(userId);
    }
}
