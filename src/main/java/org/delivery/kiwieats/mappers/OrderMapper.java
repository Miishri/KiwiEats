package org.delivery.kiwieats.mappers;

import org.delivery.kiwieats.entities.Order;
import org.delivery.kiwieats.model.OrderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Order orderDtoToOrder(OrderDTO orderDTO);
    OrderDTO orderToOrderDto(Order order);
}
