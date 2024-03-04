package org.delivery.KiwiEats.controllers;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exception.NotFoundException;
import org.delivery.KiwiEats.models.SellerDTO;
import org.delivery.KiwiEats.services.seller.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SellerController {
  public static final String SELLER_PATH = "/kiwi/seller";
  public static final String SELLER_PATH_ID = SELLER_PATH + "/{sellerId}";

  private final SellerService sellerService;

  @PostMapping(SELLER_PATH)
  public SellerDTO registerSeller(@RequestBody SellerDTO sellerDTO) {
    return sellerService.createSeller(sellerDTO);
  }

  @GetMapping(SELLER_PATH_ID)
  public SellerDTO getSellerById(@PathVariable Long sellerId) {
    log.debug("CONTROLLER - Get seller by ID - Seller ID: " + sellerId + " - CONTROLLER");

    return sellerService.getSellerById(sellerId).orElseThrow(NotFoundException::new);
  }

  @GetMapping(SELLER_PATH)
  public List<SellerDTO> getAllSellers() {
    return sellerService.getAllSellers();
  }

  @PutMapping(SELLER_PATH_ID)
  public SellerDTO updateSellerById(@PathVariable Long sellerId, @RequestBody SellerDTO sellerDTO) {

    Optional<SellerDTO> updatedProduct = sellerService.updateSeller(sellerId, sellerDTO);

    if (updatedProduct.isEmpty()) throw new NotFoundException("Seller was not found");

    return updatedProduct.get();
  }

  @DeleteMapping(SELLER_PATH_ID)
  public ResponseEntity<?> deleteSellerById(@PathVariable Long sellerId) {
    Boolean isDeleted = sellerService.deleteSellerById(sellerId);
    if (!isDeleted) throw new NotFoundException("Seller could not be deleted");
    return ResponseEntity.noContent().build();
  }
}
