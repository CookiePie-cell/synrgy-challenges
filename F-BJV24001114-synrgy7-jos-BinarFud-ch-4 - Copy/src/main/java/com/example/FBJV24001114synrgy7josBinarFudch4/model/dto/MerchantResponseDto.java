package com.example.FBJV24001114synrgy7josBinarFudch4.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponseDto {
    private UUID id;
    private String name;
    private String location;
    private Boolean open;
}
