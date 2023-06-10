package org.delivery.kiwieats.service.customer;

import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.model.customer.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> listCustomers() {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById() {
        return Optional.empty();
    }

    @Override
    public Customer saveCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public Optional<Customer> updateCustomerById(UUID customerId, Customer customer) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {
        return null;
    }
}
