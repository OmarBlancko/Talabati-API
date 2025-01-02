package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
