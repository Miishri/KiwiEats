package org.delivery.kiwieats.service.rider;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.Rider;
import org.delivery.kiwieats.entities.UserDetails;
import org.delivery.kiwieats.repositories.RiderRepository;
import org.delivery.kiwieats.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;
    private final UserDetailsRepository detailsRepository;

    @Override
    public List<Rider> listRiders() {
        return riderRepository.findAll();
    }

    @Override
    public Optional<Rider> getRiderById(UUID riderId) {
        return Optional.of(riderRepository.findById(riderId)).orElse(null);
    }

    @Override
    public Rider saveRider(Rider rider) {
        return riderRepository.save(rider);
    }

    @Override
    public Optional<Rider> updateRiderById(UUID riderId, Rider rider) {

        AtomicReference<Optional<Rider>> atomicReference = new AtomicReference<>();

        riderRepository.findById(riderId).ifPresentOrElse(riderFound -> {

            riderFound.setOrders(rider.getOrders());
            riderFound.setTips(rider.getTips());
            riderFound.setTotalPaid(rider.getTotalPaid());
            riderFound.setVerified(rider.getVerified());

            UserDetails riderDetails = detailsRepository.findByUserId(riderId);
            UserDetails riderFoundDetails = detailsRepository.findByUserId(riderFound.getId());

            riderFoundDetails.setFirstName(riderDetails.getFirstName());
            riderFoundDetails.setLastName(riderDetails.getLastName());
            riderFoundDetails.setCity(riderDetails.getCity());
            riderFoundDetails.setEmail(riderDetails.getEmail());
            riderFoundDetails.setCountry(riderDetails.getCountry());
            riderFoundDetails.setPassword(riderDetails.getPassword());
            riderFoundDetails.setPhone(riderDetails.getPhone());
            riderFoundDetails.setStreet(riderDetails.getStreet());
            riderFoundDetails.setCareOf(riderDetails.getCareOf());
            riderFoundDetails.setPostCode(riderDetails.getPostCode());
            riderFoundDetails.setRegisteredDate(riderDetails.getRegisteredDate());;

            detailsRepository.save(riderFoundDetails);

            atomicReference.set(Optional.of(riderRepository.save(riderFound)));

        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return Optional.empty();
    }

    @Override
    public Boolean deleteRiderById(UUID riderId) {
        if (riderRepository.existsById(riderId)) {
            UserDetails riderDetails = detailsRepository.findByUserId(riderId);
            detailsRepository.delete(riderDetails);
            riderRepository.deleteById(riderId);
            return true;
        }
        return false;
    }
}
