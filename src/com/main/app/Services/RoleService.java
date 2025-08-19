package com.main.app.Services;

import com.main.app.Model.Role;
import com.main.app.Repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        return role.orElse(null);
    }

    public Role findByRoleId(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        return role.orElse(null);
    }
}
