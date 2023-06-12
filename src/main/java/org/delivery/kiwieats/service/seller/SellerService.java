package org.delivery.kiwieats.service.seller;

import org.delivery.kiwieats.entities.Seller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    List<Seller> listSellers();
    Optional<Seller> getSellerById(UUID sellerId);

    Seller saveSeller(Seller seller);

    Optional<Seller> updateSellerById(UUID sellerId, Seller seller);

    Boolean deleteSellerById(UUID sellerId);
}
