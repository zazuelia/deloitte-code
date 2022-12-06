package com.example.demo.resource;

import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import com.example.demo.dao.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-task")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoTaskResource {


    TodoUserService userService;

    @Autowired
    public TodoTaskResource(TodoUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-tasks/{userName}")
    public ResponseEntity getUserTasks(@PathVariable String userName) {

        TodoUser user = userService.getTodoUserByUserName(userName);

        return ResponseEntity.ok().body(user.getTasks());
    }

    @GetMapping("/get-all-tasks")
    public ResponseEntity<List<TodoTask>> getAllTasks() {

        return ResponseEntity.ok().body(userService.getAllTasks());
    }

    @PostMapping(value = "/add-tasks", consumes = "application/json")
    public ResponseEntity addTasksToUser(@RequestBody TodoTask todoTask) {

        userService.saveTask(todoTask);

        return ResponseEntity.ok().body(todoTask);
    }

    @PatchMapping("/check-task")
    public ResponseEntity checkTask(@RequestBody TodoTask todoTask) {

        userService.checkTask(todoTask);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity deleteTaskFromUser(@PathVariable Integer taskId) {

        userService.removeTask(taskId);

        return ResponseEntity.ok().build();
    }
}
