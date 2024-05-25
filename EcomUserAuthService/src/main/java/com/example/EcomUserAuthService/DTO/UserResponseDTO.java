package com.example.EcomUserAuthService.DTO;

import com.example.EcomUserAuthService.Entity.Role;
import com.example.EcomUserAuthService.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String email;
    private List<RoleResponseDTO> roles;
    private String token;

    public static UserResponseDTO from(User user){
        if(user == null){
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.name = user.getName();
        userResponseDTO.email = user.getEmailId();
        userResponseDTO.token = user.getToken();
        userResponseDTO.roles = new ArrayList<>();

        // Convert it into lambda Stream
        for(Role role : user.getRoles()){
            RoleResponseDTO responseDTO = new RoleResponseDTO();
            responseDTO.setRole(role.getRoleName());
            responseDTO.setDesc(role.getDescription());
            userResponseDTO.roles.add(responseDTO);
        }
        return userResponseDTO;
    }
}
