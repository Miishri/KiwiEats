package org.delivery.kiwieats.repositories.seller;

import org.delivery.kiwieats.entities.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
