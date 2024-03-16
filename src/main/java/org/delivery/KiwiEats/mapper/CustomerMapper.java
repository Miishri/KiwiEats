package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.Customer;
import org.delivery.KiwiEats.models.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
  Customer customerDTOToCustomer(CustomerDTO customerDTO);

  CustomerDTO customerToCustomerDTO(Customer customer);
}
