package org.delivery.kiwieats.service.rider;

import org.delivery.kiwieats.entities.rider.Rider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RiderService {
    List<Rider> listRiders();
    Optional<Rider> getRiderById();

    Rider saveRider(Rider rider);

    Optional<Rider> updateRiderById(UUID riderId, Rider rider);

    Boolean deleteRiderById(UUID riderId);
}
