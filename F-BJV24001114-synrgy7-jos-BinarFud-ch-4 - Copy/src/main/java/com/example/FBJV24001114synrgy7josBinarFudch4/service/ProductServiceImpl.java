package com.example.FBJV24001114synrgy7josBinarFudch4.service;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.ProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.ProductResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UpdateProductRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.entity.Merchant;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.entity.Product;
import com.example.FBJV24001114synrgy7josBinarFudch4.repository.MerchantRepository;
import com.example.FBJV24001114synrgy7josBinarFudch4.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MerchantRepository merchantRepository) {
        this.productRepository = productRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public ProductResponseDto save(ProductRequestDto request) {
        Merchant merchant = merchantRepository.findById(request.getMerchantId())
                .orElseThrow(() -> new EntityNotFoundException("Merchant tidak ditemukan"));

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setMerchant(merchant);

        return mapToProductResponseDto(productRepository.save(product));
    }

    @Override
    public Page<ProductResponseDto> getProducts(String name, int page, int limit) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Pageable pageable = PageRequest.of(page, limit);
        Page<Product> products = productRepository.findAll(specification, pageable);
        return products.map(this::mapToProductResponseDto);
    }

    @Override
    public ProductResponseDto update(UUID productId, UpdateProductRequestDto request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produk tidak ditemukan"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return mapToProductResponseDto(productRepository.save(product));
    }

    @Override
    public void remove(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produk tidak ditemukan"));

        productRepository.delete(product);
    }

    private ProductResponseDto mapToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .merchantName(product.getMerchant().getName())
                .build();
    }
}
