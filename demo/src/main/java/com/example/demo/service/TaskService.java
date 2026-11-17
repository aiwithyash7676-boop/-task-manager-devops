package com.example.demo.service;

import com.example.demo.model.Task;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

@Service
public class TaskService {

    private final ConcurrentHashMap<Long, Task> tasks =
            new ConcurrentHashMap<>();

    private final AtomicLong idCounter = new AtomicLong(0);

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values())
                .stream()
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public Task createTask(String title) {
        Long id = idCounter.incrementAndGet();
        Task task = new Task(id, title, false);
        tasks.put(id, task);
        return task;
    }
}