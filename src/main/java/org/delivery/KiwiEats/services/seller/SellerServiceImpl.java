package org.delivery.KiwiEats.services.seller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.mapper.SellerMapper;
import org.delivery.KiwiEats.models.SellerDTO;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

  private final SellerRepository sellerRepository;
  private final SellerMapper sellerMapper;

  @Override
  public Optional<SellerDTO> getSellerById(Long sellerId) {
    log.debug("SERVICE - Get seller by ID - Seller ID: " + sellerId + " - SERVICE");
    return Optional.ofNullable(
        sellerMapper.sellerToSellerDto(sellerRepository.findById(sellerId).orElse(null)));
  }

  @Override
  public List<SellerDTO> getAllSellers() {
    log.debug("SERVICE - Get all sellers - SERVICE ");
    return sellerRepository.findAll().stream().map(sellerMapper::sellerToSellerDto).toList();
  }

  @Override
  public SellerDTO createSeller(SellerDTO sellerDTO) {
    return sellerMapper.sellerToSellerDto(
        sellerRepository.save(sellerMapper.sellerDtoToSeller(sellerDTO)));
  }

  @Override
  public Optional<SellerDTO> updateSeller(Long sellerId, SellerDTO sellerDTO) {
    AtomicReference<Optional<SellerDTO>> sellerReference = new AtomicReference<>();

    sellerRepository
        .findById(sellerId)
        .ifPresentOrElse(
            sellerFound -> {
              sellerFound.setUser(sellerDTO.getUser());
              sellerFound.setProductInStock(sellerDTO.getProductInStock());
              sellerFound.setEarnings(sellerDTO.getEarnings());

              sellerReference.set(
                  Optional.of(sellerMapper.sellerToSellerDto(sellerRepository.save(sellerFound))));
            },
            () -> {
              sellerReference.set(Optional.empty());
            });

    return sellerReference.get();
  }

  @Override
  public Boolean deleteSellerById(Long sellerId) {
    if (sellerRepository.existsById(sellerId)) {
      sellerRepository.deleteById(sellerId);
      return true;
    }
    return false;
  }
}
