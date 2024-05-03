package com.example.FBJV24001114synrgy7josBinarFudch4.controller;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.ProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.ProductResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UpdateProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            path = "/api/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<ProductResponseDto> showProducts(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "2") Integer limit
    ) {
        return productService.getProducts(page, limit);
    }

    @PostMapping(
            path = "/api/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductResponseDto save(@RequestBody ProductRequestDto request) {
        return productService.save(request);
    }

    @PutMapping(
            path = "/api/products/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductResponseDto update(
            @PathVariable("productId") UUID productId,
            @RequestBody UpdateProductRequestDto request) {
        return productService.update(productId, request);
    }

    @DeleteMapping(
            path = "/api/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, String> delete(@PathVariable("productId") UUID productId) {
        productService.remove(productId);
        return new HashMap<>(){{
            put("status", "Delete success");
        }};
    }
}
