package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.User;
import org.delivery.KiwiEats.models.ProductDTO;
import org.delivery.KiwiEats.models.UserDTO;
import org.hibernate.annotations.MapKeyCompositeType;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}
