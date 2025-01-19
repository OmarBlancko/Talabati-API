package com.example.talabati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.model.Role;
import com.example.talabati.repositories.RoleRepository;
@Service
public class RoleServiceImpImplementation implements RoleService{
    @Autowired
 private final RoleRepository roleRepository;
    public RoleServiceImpImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
    @Override
    public Role findByName(String name) {
    return roleRepository.findByName(name);   
    }

}
