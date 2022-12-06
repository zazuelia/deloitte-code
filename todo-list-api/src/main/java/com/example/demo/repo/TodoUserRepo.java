package com.example.demo.repo;

import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoUserRepo extends JpaRepository<TodoUser, Integer> {

    TodoUser findTodoUserByUserName(String userName);
}
