package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.roles.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
