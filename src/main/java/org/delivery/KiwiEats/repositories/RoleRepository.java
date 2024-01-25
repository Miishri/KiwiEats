package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
