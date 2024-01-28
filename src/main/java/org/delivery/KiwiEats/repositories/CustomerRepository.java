package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
/*  Customer findCustomerByEmail(String email);

  Customer findCustomerByUsername(String username);*/
}
