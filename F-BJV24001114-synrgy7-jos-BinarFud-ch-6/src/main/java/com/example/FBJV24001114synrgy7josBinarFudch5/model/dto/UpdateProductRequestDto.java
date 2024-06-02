package com.example.FBJV24001114synrgy7josBinarFudch5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDto {
    private String name;

    private Double price;
}