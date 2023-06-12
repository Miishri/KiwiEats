package org.delivery.kiwieats.repositories.rider;

import org.delivery.kiwieats.entities.rider.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RiderRepository extends JpaRepository<Rider, UUID> {
}
