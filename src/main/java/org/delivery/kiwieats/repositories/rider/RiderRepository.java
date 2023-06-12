package org.delivery.kiwieats.repositories.rider;

import org.delivery.kiwieats.entities.rider.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiderRepository extends JpaRepository<Rider, UUID> {
}
