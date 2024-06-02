package com.upc.edgelayerbackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
public class TaskController {

    private TaskService taskService;
    private TaskRepository taskRepository;

    TaskController(TaskService taskService, TaskRepository taskRepository){
        this.taskService=taskService;
        this.taskRepository=taskRepository;
    }

    @GetMapping("tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }
}
