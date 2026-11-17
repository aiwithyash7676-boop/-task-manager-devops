package com.example.demo;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void shouldCreateTask() {
        TaskService service = new TaskService();

        Task task = service.createTask("Learn DevOps");

        assertEquals(1L, task.id());
        assertEquals("Learn DevOps", task.title());
        assertFalse(task.completed());
    }

    @Test
    void shouldReturnAllTasks() {
        TaskService service = new TaskService();

        service.createTask("Learn Java");
        service.createTask("Learn Docker");

        assertEquals(2, service.getAllTasks().size());
    }
}