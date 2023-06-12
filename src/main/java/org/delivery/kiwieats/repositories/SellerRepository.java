package org.delivery.kiwieats.repositories;

import org.delivery.kiwieats.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
