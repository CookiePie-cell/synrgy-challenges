package com.example.FBJV24001114synrgy7josBinarFudch4.repository;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
