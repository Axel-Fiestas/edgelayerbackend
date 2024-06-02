package com.upc.edgelayerbackend;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task addTask(String name){
        Task task = Task.builder()
                .name(name)
                .build();

        return taskRepository.save(task);
    }
}
