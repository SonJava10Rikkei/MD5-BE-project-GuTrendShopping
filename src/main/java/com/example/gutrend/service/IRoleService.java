package com.example.gutrend.service;

import com.example.gutrend.model.Role;
import com.example.gutrend.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
