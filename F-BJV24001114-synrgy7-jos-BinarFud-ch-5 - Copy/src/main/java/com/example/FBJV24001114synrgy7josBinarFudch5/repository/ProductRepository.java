package com.example.FBJV24001114synrgy7josBinarFudch5.repository;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
