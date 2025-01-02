package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.talabati.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
