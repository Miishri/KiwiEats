package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {}
