package org.delivery.KiwiEats.repositories;

import java.util.Optional;
import org.delivery.KiwiEats.entities.roles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
