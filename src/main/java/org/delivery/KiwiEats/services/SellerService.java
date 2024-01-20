package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.models.SellerDTO;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Optional<SellerDTO> getSellerById(Long sellerId);
    List<SellerDTO> getAllSellers();
    SellerDTO createSeller(SellerDTO sellerDTO);
    Optional<SellerDTO> updateSeller(Long sellerId, SellerDTO sellerDTO);
    Boolean deleteSellerById(Long sellerId);
}
