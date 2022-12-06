package com.example.demo.model;

public enum TodoUserPermissions {

    TASKUSER_READ("taskuser:read"),
    TASKUSER_WRITE("taskuser:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    TodoUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
