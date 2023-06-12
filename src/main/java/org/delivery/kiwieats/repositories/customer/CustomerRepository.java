package org.delivery.kiwieats.repositories.customer;

import org.delivery.kiwieats.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
