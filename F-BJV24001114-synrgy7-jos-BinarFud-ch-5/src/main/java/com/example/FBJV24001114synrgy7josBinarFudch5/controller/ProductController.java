package com.example.FBJV24001114synrgy7josBinarFudch5.controller;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.ApiResponse;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.PagingResponse;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.ProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.ProductResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UpdateProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(
            value = "/api/products"
    )
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>>showProducts(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        Page<ProductResponseDto> products = productService.getProducts(name, page, limit);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<List<ProductResponseDto>>builder()
                        .statusCode(200)
                        .data(products.getContent())
                        .paging(PagingResponse.builder().
                                currentPage(products.getNumber())
                                .totalPages(products.getTotalPages())
                                .size(products.getSize())
                                .build())
                        .build());
    }

    @PostMapping(
            value = "/api/products"
    )
    public ResponseEntity<ApiResponse<ProductResponseDto>> save(@RequestBody ProductRequestDto request) {
        ProductResponseDto response = productService.save(request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<ProductResponseDto>builder()
                        .statusCode(201)
                        .data(response)
                        .build());
    }

    @PutMapping(
            value = "/api/products/{productId}"
    )
    public ResponseEntity<ApiResponse<ProductResponseDto>> update(
            @PathVariable("productId") UUID productId,
            @RequestBody UpdateProductRequestDto request) {
        ProductResponseDto response = productService.update(productId, request);

        return ResponseEntity
                .ok()
                .body(ApiResponse.<ProductResponseDto>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }

    @DeleteMapping(
            value = "/api/products/{productId}"
    )
    public ResponseEntity<ApiResponse<Map<String, String>>> delete(@PathVariable("productId") UUID productId) {
        productService.remove(productId);
        Map<String, String> response = new HashMap<>(){{
            put("status", "Delete success");
        }};
        return ResponseEntity
                .ok()
                .body(ApiResponse.<Map<String, String>>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }
}
