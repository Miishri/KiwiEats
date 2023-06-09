package org.delivery.kiwieats.mappers.customer;

import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.model.customer.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO customerDTOl);

    CustomerDTO customerToCustomerDto(Customer customer);
}
