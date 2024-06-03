package com.flavowcase.controller;

import com.flavowcase.dto.ShopCreateRequest;
import com.flavowcase.dto.ShopCreateResponse;
import com.flavowcase.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopCreateResponse> shopCreate(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody ShopCreateRequest request
    ) {
        Long shopId = shopService.createShop(token, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ShopCreateResponse.builder()
                        .id(shopId)
                        .build()
                );
    }
}
