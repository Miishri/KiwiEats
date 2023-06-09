package org.delivery.kiwieats.service.rider;

import org.delivery.kiwieats.model.rider.RiderDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RiderService {
    List<RiderDTO> listRiders();
    Optional<RiderDTO> getRiderById();

    RiderDTO saveRider(RiderDTO riderDTO);

    Optional<RiderDTO> updateRiderById(UUID riderId, RiderDTO riderDTO);

    Boolean deleteRiderById(UUID riderId);
}
