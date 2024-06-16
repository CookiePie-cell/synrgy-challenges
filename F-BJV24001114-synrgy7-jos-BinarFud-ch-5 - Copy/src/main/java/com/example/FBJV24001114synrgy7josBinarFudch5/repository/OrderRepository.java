package com.example.FBJV24001114synrgy7josBinarFudch5.repository;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.Order;
import com.example.FBJV24001114synrgy7josBinarFudch5.projection.OrderDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>{
    @Query("SELECT o.id as orderId, o.orderTime AS orderTime, p.name AS productName, od.quantity AS quantity, od.totalPrice AS totalPrice \n" +
            "FROM Order o\n" +
            "INNER JOIN OrderDetail od ON o.id = od.order.id\n" +
            "INNER JOIN Product p ON od.product.id = p.id \n" +
            "WHERE o.user.id = :userId")
    List<OrderDetailProjection> getOrderDetailsByUserId(@Param("userId") UUID userId);
}
