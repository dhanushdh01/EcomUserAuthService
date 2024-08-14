package com.example.EcomUserAuthService.Service;

import com.example.EcomUserAuthService.DTO.LoginRequestDTO;
import com.example.EcomUserAuthService.DTO.SignupRequestDTO;
import com.example.EcomUserAuthService.DTO.UserResponseDTO;
import com.example.EcomUserAuthService.Entity.Role;
import com.example.EcomUserAuthService.Entity.User;
import com.example.EcomUserAuthService.Exception.InvalidCredentialException;
import com.example.EcomUserAuthService.Exception.RoleNotFoundException;
import com.example.EcomUserAuthService.Exception.UserNotFoundException;
import com.example.EcomUserAuthService.Repository.RoleRepository;
import com.example.EcomUserAuthService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserResponseDTO signup(SignupRequestDTO signupRequestDTO) throws RoleNotFoundException {
        Role role = roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                () -> new RoleNotFoundException("Role not found")
        );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setName(signupRequestDTO.getName());
        user.setEmailId(signupRequestDTO.getEmail());
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        user.setRoles(List.of(role));

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User savedUser = userRepository.findByEmailId(loginRequestDTO.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        if(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            String userData = savedUser.getEmailId() + savedUser.getPassword() + LocalDateTime.now();
            String token = bCryptPasswordEncoder.encode(userData);
            savedUser.setToken(token);
        } else {
            throw new InvalidCredentialException();
        }
        savedUser = userRepository.save(savedUser);
        return UserResponseDTO.from(savedUser);
    }

    @Override
    public boolean validateToken(String token) {
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        return true;
    }

    @Override
    public boolean logout(String token) {
        User savedUser = userRepository.findByToken(token).orElseThrow(
                () -> new InvalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }


}