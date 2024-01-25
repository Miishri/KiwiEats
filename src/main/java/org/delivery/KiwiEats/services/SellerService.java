package org.delivery.KiwiEats.services;

import java.util.List;
import java.util.Optional;
import org.delivery.KiwiEats.models.SellerDTO;

public interface SellerService {
  Optional<SellerDTO> getSellerById(Long sellerId);

  List<SellerDTO> getAllSellers();

  SellerDTO createSeller(SellerDTO sellerDTO);

  Optional<SellerDTO> updateSeller(Long sellerId, SellerDTO sellerDTO);

  Boolean deleteSellerById(Long sellerId);
}
