package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.models.SellerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    Optional<SellerDTO> getSellerById(UUID uuid);
    List<SellerDTO> getAllSellers();
    SellerDTO createSeller(SellerDTO sellerDTO);
    Optional<SellerDTO> updateSeller(UUID uuid, SellerDTO sellerDTO);
    Boolean deleteSellerById(UUID uuid);
}
