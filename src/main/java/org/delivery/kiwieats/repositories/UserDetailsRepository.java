package org.delivery.kiwieats.repositories;


import org.delivery.kiwieats.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserId(UUID userId);
}
