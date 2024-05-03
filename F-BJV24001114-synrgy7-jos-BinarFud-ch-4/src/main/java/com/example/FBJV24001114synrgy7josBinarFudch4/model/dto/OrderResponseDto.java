package com.example.FBJV24001114synrgy7josBinarFudch4.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private UUID id;

    private Timestamp orderTime;

    private String destinationAddress;

    private Integer quantity;

    private Double totalPrice;
}
