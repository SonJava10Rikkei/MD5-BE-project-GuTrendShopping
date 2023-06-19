package com.example.gutrend.repository;

import com.example.gutrend.model.Role;
import com.example.gutrend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(RoleName name);
}
