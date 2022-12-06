package com.example.demo.repo;

import com.example.demo.model.TodoRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRoleRepo extends JpaRepository<TodoRole, Integer> {

    TodoRole findTodoRolesByRoleName(String roleName);
}
