package com.example.gutrend.service.impl;

import com.example.gutrend.model.Role;
import com.example.gutrend.model.RoleName;
import com.example.gutrend.repository.IRoleRepository;
import com.example.gutrend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
