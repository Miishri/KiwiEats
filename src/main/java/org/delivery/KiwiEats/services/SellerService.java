package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.models.SellerDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    Optional<SellerDTO> getSellerById(UUID uuid);
    List<SellerDTO> getSellers();
    SellerDTO createSeller(SellerDTO sellerDTO);
    Optional<SellerDTO> updateSeller(UUID uuid, SellerDTO sellerDTO);
    Boolean updateSellerById(UUID uuid);
}
