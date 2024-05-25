package com.example.EcomUserAuthService.Service;

import com.example.EcomUserAuthService.DTO.LoginRequestDTO;
import com.example.EcomUserAuthService.DTO.SignupRequestDTO;
import com.example.EcomUserAuthService.DTO.UserResponseDTO;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    UserResponseDTO signup(SignupRequestDTO signupRequestDTO) throws RoleNotFoundException;
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    boolean logout(String token);
}
