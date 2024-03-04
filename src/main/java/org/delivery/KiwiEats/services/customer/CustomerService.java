package org.delivery.KiwiEats.services.customer;

import java.util.Optional;
import org.delivery.KiwiEats.models.CustomerDTO;

public interface CustomerService {

  Optional<CustomerDTO> getCustomerById(Long customerId);

  CustomerDTO addCustomer(CustomerDTO customerDTO);

  Optional<CustomerDTO> modifyCustomer(Long customerId, CustomerDTO customerDTO);

  Boolean deleteCustomer(Long customerId);
}
