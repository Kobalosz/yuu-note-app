package com.example.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskController(){
        tasks.add(new Task(11, "name1","Learn Spring Boot"));
        tasks.add(new Task(22, "name2","Build an API"));
        // tasks.add(new Task(22, "name2","Build an API"));

    }

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // POST: http://localhost:8080/api/tasks
    @PostMapping
    public Task createTask(@RequestBody Task newTask) {
        tasks.add(newTask);
        return newTask;
    }
}
