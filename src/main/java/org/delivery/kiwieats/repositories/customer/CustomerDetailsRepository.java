package org.delivery.kiwieats.repositories.customer;

import org.delivery.kiwieats.entities.customer.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
}
