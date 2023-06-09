package org.delivery.kiwieats.mappers.seller;


import org.delivery.kiwieats.entities.seller.Seller;
import org.delivery.kiwieats.model.seller.SellerDTO;
import org.mapstruct.Mapper;
@Mapper
public interface SellerMapper {
    Seller sellerDtoToSeller(SellerDTO sellerDTO);

    SellerDTO sellerToSellerDto(Seller seller);
}
