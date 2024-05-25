package com.example.EcomUserAuthService.Service;

import com.example.EcomUserAuthService.DTO.RoleRequestDTO;
import com.example.EcomUserAuthService.DTO.RoleResponseDTO;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
}
