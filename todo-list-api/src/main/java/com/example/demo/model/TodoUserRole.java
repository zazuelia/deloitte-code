package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

import static com.example.demo.model.TodoUserPermissions.*;

public enum TodoUserRole {

    ROLE_ADMIN(Arrays.asList(TASKUSER_READ, TASKUSER_WRITE)),
    ROLE_TASKUSER(Arrays.asList(TASKUSER_READ, TASKUSER_WRITE, USER_READ, USER_WRITE));

    private final List<TodoUserPermissions> permissions;

    TodoUserRole(List<TodoUserPermissions> permissions) {
        this.permissions = permissions;
    }
}
