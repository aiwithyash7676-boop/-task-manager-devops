package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<?> addTask(
            @RequestBody Map<String, String> request) {

        String title = request.get("title");

        if (title == null || title.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Task title is required"));
        }

        Task task = taskService.createTask(title.trim());

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}