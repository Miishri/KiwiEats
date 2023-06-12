package org.delivery.kiwieats.repositories;

import org.delivery.kiwieats.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiderRepository extends JpaRepository<Rider, UUID> {
}
