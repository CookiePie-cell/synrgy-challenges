package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.ProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.ProductResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UpdateProductRequestDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProductService {
    ProductResponseDto save(ProductRequestDto request);

    Page<ProductResponseDto> getProducts(String name, int page, int limit);

    ProductResponseDto update(UUID productId, UpdateProductRequestDto request);

    void remove(UUID productId);

}
