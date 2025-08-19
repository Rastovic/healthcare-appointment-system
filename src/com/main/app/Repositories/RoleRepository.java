package com.main.app.Repositories;

import com.main.app.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findById(Long id);   // Spring Data already understands "id"


}
