package com.example.FBJV24001114synrgy7josBinarFudch4.service;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UpdateMerchantStatusDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.entity.Merchant;
import com.example.FBJV24001114synrgy7josBinarFudch4.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public List<MerchantResponseDto> showOpenMerchant() {
        List<Merchant> openMerchants = merchantRepository.findAllByOpenTrue();
        return openMerchants.stream().map(this::mapToMerchantResponseDto).toList();
    }

    @Override
    public MerchantResponseDto save(MerchantRequestDto request) {
        Merchant merchant = new Merchant();
        merchant.setId(UUID.randomUUID());
        merchant.setName(request.getName());
        merchant.setLocation(request.getLocation());
        merchant.setOpen(true);
        return mapToMerchantResponseDto(merchantRepository.save(merchant));
    }

    @Override
    public MerchantResponseDto updateStatus(UUID merchantId, UpdateMerchantStatusDto request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new EntityNotFoundException("Merchant tidak ditemukan"));

        merchant.setOpen(request.getOpen());
        return mapToMerchantResponseDto(merchantRepository.save(merchant));
    }

    private MerchantResponseDto mapToMerchantResponseDto(Merchant merchant) {
        return MerchantResponseDto.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .location(merchant.getLocation())
                .open(merchant.getOpen())
                .build();
    }
}
