package com.example.FBJV24001114synrgy7josBinarFudch4.projection;

import java.sql.Timestamp;
import java.util.UUID;

public interface OrderDetailProjection {
    UUID getOrderId();
    Timestamp getOrderTime();
    String getProductName();
    Integer getQuantity();
    Double getTotalPrice();

}
