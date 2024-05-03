package com.example.FBJV24001114synrgy7josBinarFudch4.controller;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.MerchantResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UpdateMerchantStatusDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping(
            path = "/api/merchants",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<MerchantResponseDto> getOpenMerchants() {
        return merchantService.showOpenMerchant();
    }

    @PostMapping(
            path = "/api/merchants",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MerchantResponseDto save(@RequestBody MerchantRequestDto request) {
        return merchantService.save(request);
    }

    @PatchMapping(
            path = "/api/merchants/{merchantId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MerchantResponseDto updateStatus(
            @PathVariable("merchantId") UUID merchantId,
            @RequestBody UpdateMerchantStatusDto request
    ) {
        return merchantService.updateStatus(merchantId, request);
    }
}
