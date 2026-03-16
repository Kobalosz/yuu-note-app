package com.example.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final AtomicLong atomic = new AtomicLong();
    public TaskController(){
        tasks.add(new Task(atomic.incrementAndGet(), "name1","Learn Spring Boot"));
        tasks.add(new Task(atomic.incrementAndGet(), "name2","Build an API"));
        tasks.add(new Task(atomic.incrementAndGet(), "name3","Build an API"));

        // tasks.add(new Task(22, "name2","Build an API"));

    }

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // POST: http://localhost:8080/api/tasks
    @PostMapping
    public Task createTask(@RequestBody Task newTask) {
        
        Task t = new Task(atomic.incrementAndGet(),newTask.name(),newTask.content());
        System.err.println(t);
        tasks.add(t);
        return t;
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable int id){
        int index = -1;

        for(Task t : tasks){
            index++;
            if(t.id() == id){
                tasks.remove(index);
                return t;

            }
        }


        // return null if task is not in list
        return null;
    }


    @PutMapping("/api/task")
    public Task editTask(@RequestBody Task task){
        Integer index = -1;
        Task nt = null;
        for(Task t : tasks){
            index++;
            if(t.id() == task.id()){
                tasks.set(index, task);
                return task;
            }
        }
        return nt;
    }
}
