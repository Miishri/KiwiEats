package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.models.SellerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerMapper {
  Seller sellerDtoToSeller(SellerDTO sellerDTO);

  SellerDTO sellerToSellerDto(Seller seller);
}
