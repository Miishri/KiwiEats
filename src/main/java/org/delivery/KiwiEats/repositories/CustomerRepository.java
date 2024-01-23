package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail(String email);

    Customer findCustomerByUsername(String username);

}
