package com.example.EcomUserAuthService.Service;

import com.example.EcomUserAuthService.DTO.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TaskServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String TASK_SERVICE_URL = "http://localhost:8081/tasks";

    public List<TaskDTO> getAllTasks() {
        return restTemplate.getForObject(TASK_SERVICE_URL, List.class);
    }

    public TaskDTO getTaskById(Long id) {
        return restTemplate.getForObject(TASK_SERVICE_URL + "/" + id, TaskDTO.class);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        return restTemplate.postForObject(TASK_SERVICE_URL, taskDTO, TaskDTO.class);
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        restTemplate.put(TASK_SERVICE_URL + "/" + id, taskDTO);
    }

    public void deleteTask(Long id) {
        restTemplate.delete(TASK_SERVICE_URL + "/" + id);
    }
}