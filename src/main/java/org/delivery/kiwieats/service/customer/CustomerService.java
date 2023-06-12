package org.delivery.kiwieats.service.customer;

import org.delivery.kiwieats.entities.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Optional<Customer> getCustomerById(UUID customerId);

    Customer saveCustomer(Customer customer);

    Optional<Customer> updateCustomerById(UUID customerId, Customer customer);

    Boolean deleteCustomerById(UUID customerId);

}
