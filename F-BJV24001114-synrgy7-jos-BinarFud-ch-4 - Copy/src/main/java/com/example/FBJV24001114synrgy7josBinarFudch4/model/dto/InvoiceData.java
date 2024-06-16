package com.example.FBJV24001114synrgy7josBinarFudch4.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceData {
    private String orderId;
    private Timestamp orderTime;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
}
