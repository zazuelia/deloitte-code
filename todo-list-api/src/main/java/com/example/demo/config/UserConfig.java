package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.dao.TodoUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(TodoUserService todoUserService) {

        return args -> {
            todoUserService.saveRole(new TodoRole(null, "ROLE_ADMIN"));
            todoUserService.saveRole(new TodoRole(null, "ROLE_TASKUSER"));

            todoUserService.saveTodoUser(new TodoUser(null,
                                                      "jelia",
                                                      "Jose Elia",
                                                      "123",
                                                      true,
                                                      true,
                                                      true,
                                                      true,
                                                       new ArrayList<>(),
                                                       new ArrayList<>(),
                                                       new ArrayList<>()));

            todoUserService.saveTodoUser(new TodoUser(null,
                    "jbravo",
                    "Joao Bravo",
                    "456",
                    true,
                    true,
                    true,
                    true,
                    Arrays.asList(todoUserService.getRoleByRoleName(TodoUserRole.ROLE_TASKUSER.name())),
                    new ArrayList<>(),
                    new ArrayList<>()));

            todoUserService.addRoleToUser("ROLE_ADMIN", "jelia");

            todoUserService.saveTask(new TodoTask(null, "Take Rafa to school", new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), false, todoUserService.getTodoUserByUserName("jelia")));

        };
    }
}
