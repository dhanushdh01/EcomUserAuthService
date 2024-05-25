package com.example.EcomUserAuthService.Service;

import com.example.EcomUserAuthService.DTO.RoleRequestDTO;
import com.example.EcomUserAuthService.DTO.RoleResponseDTO;
import com.example.EcomUserAuthService.Entity.Role;
import com.example.EcomUserAuthService.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setDescription(roleRequestDTO.getDescription());
        return RoleResponseDTO.from(roleRepository.save(role));
    }
}
