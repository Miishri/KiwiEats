package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.models.CustomerDTO;

import java.util.Optional;

public interface CustomerService {

  Optional<CustomerDTO> getCustomerById(Long customerId);

  CustomerDTO addCustomer(CustomerDTO customerDTO);

  Optional<CustomerDTO> modifyCustomer(Long customerId, CustomerDTO customerDTO);

  Boolean deleteCustomer(Long customerId);
}
