package org.delivery.kiwieats.service.customer;

import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.model.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Optional<Customer> getCustomerById();

    Customer saveCustomer(CustomerDTO customer);

    Optional<Customer> updateCustomerById(UUID customerId, Customer customer);

    Boolean deleteCustomerById(UUID customerId);

}
