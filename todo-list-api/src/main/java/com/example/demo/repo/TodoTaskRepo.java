package com.example.demo.repo;

import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTaskRepo extends JpaRepository<TodoTask, Integer> {

    TodoTask findByTaskName(String taskName);
    void deleteTodoTaskByTaskNameAndTodoUser(String taskName, TodoUser todoUser);
}
