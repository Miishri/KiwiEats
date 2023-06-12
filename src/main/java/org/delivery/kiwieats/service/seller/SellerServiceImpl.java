package org.delivery.kiwieats.service.seller;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.Seller;
import org.delivery.kiwieats.entities.UserDetails;
import org.delivery.kiwieats.repositories.SellerRepository;
import org.delivery.kiwieats.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final UserDetailsRepository detailsRepository;

    @Override
    public List<Seller> listSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(UUID sellerId) {
        return Optional.of(sellerRepository.findById(sellerId))
                .orElse(null);
    }

    @Override
    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> updateSellerById(UUID sellerId, Seller seller) {

        AtomicReference<Optional<Seller>> atomicReference = new AtomicReference<>();

        sellerRepository.findById(sellerId).ifPresentOrElse(sellerFound -> {

            sellerFound.setProducts(seller.getProducts());
            sellerFound.setVerified(seller.getVerified());
            sellerFound.setRevenue(seller.getRevenue());
            sellerFound.setTotalCustomers(seller.getTotalCustomers());

            UserDetails sellerDetails = detailsRepository.findByUserId(sellerId);
            UserDetails sellerFoundDetails = detailsRepository.findByUserId(sellerFound.getId());

            sellerFoundDetails.setFirstName(sellerDetails.getFirstName());
            sellerFoundDetails.setLastName(sellerDetails.getLastName());
            sellerFoundDetails.setEmail(sellerDetails.getEmail());
            sellerFoundDetails.setCity(sellerDetails.getCity());
            sellerFoundDetails.setCountry(sellerDetails.getCountry());
            sellerFoundDetails.setPhone(sellerDetails.getPhone());
            sellerFoundDetails.setPassword(sellerDetails.getPassword());
            sellerFoundDetails.setStreet(sellerDetails.getStreet());
            sellerFoundDetails.setCareOf(sellerDetails.getCareOf());
            sellerFoundDetails.setPostCode(sellerDetails.getPostCode());
            sellerFoundDetails.setRegisteredDate(sellerDetails.getRegisteredDate());

            detailsRepository.save(sellerFoundDetails);

            atomicReference.set(Optional.of(sellerRepository.save(sellerFound)));

        },() -> {
            atomicReference.set(Optional.empty());
        });

        return Optional.empty();
    }

    @Override
    public Boolean deleteSellerById(UUID sellerId) {
        if (sellerRepository.existsById(sellerId)) {
            UserDetails sellerDetails = detailsRepository.findByUserId(sellerId);
            detailsRepository.delete(sellerDetails);
            sellerRepository.deleteById(sellerId);
            return true;
        }
        return false;
    }
}
