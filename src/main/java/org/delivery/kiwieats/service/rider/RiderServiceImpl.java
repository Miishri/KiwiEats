package org.delivery.kiwieats.service.rider;

import org.delivery.kiwieats.entities.rider.Rider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RiderServiceImpl implements RiderService {
    @Override
    public List<Rider> listRiders() {
        return null;
    }

    @Override
    public Optional<Rider> getRiderById() {
        return Optional.empty();
    }

    @Override
    public Rider saveRider(Rider rider) {
        return null;
    }

    @Override
    public Optional<Rider> updateRiderById(UUID riderId, Rider rider) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteRiderById(UUID riderId) {
        return null;
    }
}
