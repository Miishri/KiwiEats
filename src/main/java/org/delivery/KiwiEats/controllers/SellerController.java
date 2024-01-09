package org.delivery.KiwiEats.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.services.SellerService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SellerController {

    public static final String SELLER_PATH = "/kiwi/seller";
    public static final String SELLER_PATH_ID = SELLER_PATH + "/{sellerId}";

    private final SellerService sellerService;
    private final SellerModelAssembler sellerModelAssembler;



}
