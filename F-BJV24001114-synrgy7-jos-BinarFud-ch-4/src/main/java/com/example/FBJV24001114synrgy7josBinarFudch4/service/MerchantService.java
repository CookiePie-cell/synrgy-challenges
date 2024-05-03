package com.example.FBJV24001114synrgy7josBinarFudch4.service;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UpdateMerchantStatusDto;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<MerchantResponseDto> showOpenMerchant();
    MerchantResponseDto save(MerchantRequestDto request);

    MerchantResponseDto updateStatus(UUID merchantId, UpdateMerchantStatusDto request);
}
