package org.delivery.kiwieats.service.rider;

import org.delivery.kiwieats.model.rider.RiderDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RiderServiceImpl implements RiderService {
    @Override
    public List<RiderDTO> listRiders() {
        return null;
    }

    @Override
    public Optional<RiderDTO> getRiderById() {
        return Optional.empty();
    }

    @Override
    public RiderDTO saveRider(RiderDTO riderDTO) {
        return null;
    }

    @Override
    public Optional<RiderDTO> updateRiderById(UUID riderId, RiderDTO riderDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteRiderById(UUID riderId) {
        return null;
    }
}
