package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
