package com.example.demo.dao;

import com.example.demo.model.TodoRole;
import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TodoUserService extends UserDetailsService {

    void saveTodoUser(TodoUser todoUser);
    void deleteTodoUser(TodoUser todoUser);
    List<TodoUser> getAllTodoUsers();
    TodoUser getTodoUserByUserName(String userName);
    void saveRole(TodoRole todoRole);
    void saveTask(TodoTask todoTask);
    List<TodoRole> getRoles();
    TodoRole getRoleByRoleName(String roleName);
    void addRoleToUser(String roleName, String userName);
    void addTaskToUser(String taskName, String userName);
    void removeTask(Integer taskId);
    void removeRoleFromUser(String roleName, String userName);
    List<TodoTask> getAllTasks();
    void checkTask(TodoTask todoTask);
}
