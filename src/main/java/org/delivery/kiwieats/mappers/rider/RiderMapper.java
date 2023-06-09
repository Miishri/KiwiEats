package org.delivery.kiwieats.mappers.rider;

import org.delivery.kiwieats.entities.rider.Rider;
import org.delivery.kiwieats.model.rider.RiderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RiderMapper {
    Rider riderDtoToRider(RiderDTO riderDTO);

    RiderDTO riderToRiderDto(Rider rider);
}
