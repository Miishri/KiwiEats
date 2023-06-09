package org.delivery.kiwieats.service.seller;

import org.delivery.kiwieats.model.seller.SellerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    List<SellerDTO> listSellers();
    Optional<SellerDTO> getSellerById();

    SellerDTO saveSeller(SellerDTO sellerDTO);

    Optional<SellerDTO> updateSellerById(UUID sellerId, SellerDTO sellerDTO);

    Boolean deleteSellerById(UUID sellerId);
}
