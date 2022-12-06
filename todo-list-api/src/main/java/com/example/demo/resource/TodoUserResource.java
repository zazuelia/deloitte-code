package com.example.demo.resource;

import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import com.example.demo.repo.TodoUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo-user")
@RequiredArgsConstructor
public class TodoUserResource {

    @Autowired
    private TodoUserRepo userRepo;

    @GetMapping("get-users")
    public ResponseEntity<List<TodoUser>> getUsers() {

        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("add-task")
    public ResponseEntity<TodoTask> addTaskToUser(@RequestBody TodoTask todoTask) {

        return ResponseEntity.ok().body(todoTask);
    }
}
