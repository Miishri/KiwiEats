package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.models.SellerDTO;
import org.hibernate.annotations.MapKeyCompositeType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {
    Seller sellerDtoToSeller(SellerDTO sellerDTO);
    SellerDTO sellerToSellerDto(Seller seller);
}
