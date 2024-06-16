package com.example.FBJV24001114synrgy7josBinarFudch4.repository;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findAllByOpenTrue();
}
