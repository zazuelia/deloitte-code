package com.example.demo.dao;

import com.example.demo.model.TodoRole;
import com.example.demo.model.TodoTask;
import com.example.demo.model.TodoUser;
import com.example.demo.repo.TodoRoleRepo;
import com.example.demo.repo.TodoTaskRepo;
import com.example.demo.repo.TodoUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TodoUserDAO implements TodoUserService {

    private TodoUserRepo userRepo;
    private TodoRoleRepo roleRepo;
    private TodoTaskRepo taskRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TodoUserDAO(TodoUserRepo userRepo, TodoRoleRepo roleRepo, TodoTaskRepo taskRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.taskRepo = taskRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TodoUser todoUser = userRepo.findTodoUserByUserName(username);

        if (todoUser == null) {

            throw new UsernameNotFoundException(String.format("User %s not found in the database", username));
        }

        return todoUser;
    }

    @Override
    public void saveTodoUser(TodoUser todoUser) {

        todoUser.setPassword(passwordEncoder.encode(todoUser.getPassword()));

        userRepo.save(todoUser);
    }

    @Override
    public void deleteTodoUser(TodoUser todoUser) {

        userRepo.delete(todoUser);
    }

    @Override
    public List<TodoUser> getAllTodoUsers() {

        return userRepo.findAll();
    }

    @Override
    public TodoUser getTodoUserByUserName(String userName) {

        return userRepo.findTodoUserByUserName(userName);
    }

    @Override
    public void saveRole(TodoRole todoRole) {

        roleRepo.save(todoRole);
    }

    @Override
    public void saveTask(TodoTask todoTask) {

        Timestamp taskDate = new Timestamp(new Date().getTime());
        Timestamp lastUpdate = new Timestamp(new Date().getTime());
        todoTask.setTaskDate(taskDate);
        todoTask.setLastUpdate(lastUpdate);
        taskRepo.save(todoTask);
    }

    @Override
    public List<TodoRole> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public TodoRole getRoleByRoleName(String roleName) {
        return roleRepo.findTodoRolesByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String roleName, String userName) {

        TodoUser user = userRepo.findTodoUserByUserName(userName);
        TodoRole role = roleRepo.findTodoRolesByRoleName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public void addTaskToUser(String taskName, String userName) {

        TodoUser user = userRepo.findTodoUserByUserName(userName);
        TodoTask task = taskRepo.findByTaskName(taskName);

        user.getTasks().add(task);
    }

    @Override
    public void removeTask(Integer taskId) {

        taskRepo.deleteById(taskId);
    }

    @Override
    public void removeRoleFromUser(String roleName, String userName) {

        TodoUser user = userRepo.findTodoUserByUserName(userName);
        TodoRole role = roleRepo.findTodoRolesByRoleName(roleName);
        user.getRoles().remove(role);
    }

    @Override
    public List<TodoTask> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public void checkTask(TodoTask todoTask) {

        TodoTask task = taskRepo.findById(todoTask.getTaskId()).get();
        task.setChecked(todoTask.isChecked());
         taskRepo.save(task);
    }


}
