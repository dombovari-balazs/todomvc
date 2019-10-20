package com.dombi.advanced.todomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dombi.advanced.todomvc.model.VehicleAppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<VehicleAppUser,Long> {
    Optional<VehicleAppUser> findByUsername(String username);
}
