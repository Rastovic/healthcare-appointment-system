package com.main.app.Services;

import com.main.app.Model.Role;
import com.main.app.Repositories.RoleRepository;

import java.util.Optional;

public class RoleService {

    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        return role.orElse(null);
    }
}
