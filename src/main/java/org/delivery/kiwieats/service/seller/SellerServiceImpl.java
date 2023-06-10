package org.delivery.kiwieats.service.seller;

import org.delivery.kiwieats.entities.seller.Seller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SellerServiceImpl implements SellerService {
    @Override
    public List<Seller> listSellers() {
        return null;
    }

    @Override
    public Optional<Seller> getSellerById() {
        return Optional.empty();
    }

    @Override
    public Seller saveSeller(Seller seller) {
        return null;
    }

    @Override
    public Optional<Seller> updateSellerById(UUID sellerId, Seller seller) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteSellerById(UUID sellerId) {
        return null;
    }
}
