package com.example.EcomUserAuthService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "ECOM_USER")
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String emailId;
    private String password; // won't store acctual password
    private String token; // temporary solution

    @ManyToMany
    private List<Role> roles;
}
