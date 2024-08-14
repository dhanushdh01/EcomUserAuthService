package com.example.EcomUserAuthService.Controller;

import com.example.EcomUserAuthService.DTO.*;
import com.example.EcomUserAuthService.Service.TaskServiceClient;
import com.example.EcomUserAuthService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskServiceClient taskServiceClient;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String authToken){
        return ResponseEntity.ok(userService.logout(authToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.signup(signupRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader("Authorization") String authToken){
        return ResponseEntity.ok(userService.validateToken(authToken));
    }

    // Task Management Endpoints

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskServiceClient.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskServiceClient.getTaskById(id));
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskServiceClient.createTask(taskDTO));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        taskServiceClient.updateTask(id, taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskServiceClient.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}