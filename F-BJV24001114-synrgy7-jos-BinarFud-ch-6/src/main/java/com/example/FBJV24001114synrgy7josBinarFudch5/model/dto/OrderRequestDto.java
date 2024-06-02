package com.example.FBJV24001114synrgy7josBinarFudch5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private UUID userId;

    private UUID productId;

    private String destinationAddress;

    private Integer quantity;
}
