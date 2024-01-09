package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {

}
