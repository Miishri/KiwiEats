package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.models.CustomerDTO;

import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerByUUID(Long uuid);

    Optional<CustomerDTO> getCustomerByEmail(String emailId);

    Optional<CustomerDTO> getCustomerByUsername(String nickName);

    CustomerDTO addCustomer(CustomerDTO customerDTO);

    Optional<CustomerDTO> modifyCustomer(Long uuid, CustomerDTO customerDTO);

    Boolean deleteCustomer(Long uuid);
}
