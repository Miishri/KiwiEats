package org.delivery.kiwieats.service.customer;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.model.customer.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<CustomerDTO> listCustomers() {
        return null;
    }

    @Override
    public Optional<CustomerDTO> getCustomerById() {
        return Optional.empty();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {
        return null;
    }
}
