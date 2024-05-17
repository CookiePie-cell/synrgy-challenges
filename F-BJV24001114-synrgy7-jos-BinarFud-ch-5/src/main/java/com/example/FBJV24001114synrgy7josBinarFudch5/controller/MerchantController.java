package com.example.FBJV24001114synrgy7josBinarFudch5.controller;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.ApiResponse;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.MerchantRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.MerchantResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UpdateMerchantStatusDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<MerchantResponseDto>>> getOpenMerchants() {
        List<MerchantResponseDto> response = merchantService.showOpenMerchant();
        return ResponseEntity
                .ok()
                .body(ApiResponse.<List<MerchantResponseDto>>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }

    @PostMapping(
            path = "/api/merchants",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<MerchantResponseDto>> save(@RequestBody MerchantRequestDto request) {
        MerchantResponseDto response = merchantService.save(request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<MerchantResponseDto>builder()
                        .statusCode(201)
                        .data(response)
                        .build());
    }

    @PatchMapping(
            path = "/api/merchants/{merchantId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<MerchantResponseDto>> updateStatus(
            @PathVariable("merchantId") UUID merchantId,
            @RequestBody UpdateMerchantStatusDto request
    ) {
        MerchantResponseDto response = merchantService.updateStatus(merchantId, request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<MerchantResponseDto>builder()
                        .statusCode(201)
                        .data(response)
                        .build());
    }
}
