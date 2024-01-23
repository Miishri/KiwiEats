package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.models.SellerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SellerMapper {
  Seller sellerDtoToSeller(SellerDTO sellerDTO);

  SellerDTO sellerToSellerDto(Seller seller);
}
