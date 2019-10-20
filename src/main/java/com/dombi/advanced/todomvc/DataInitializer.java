package com.dombi.advanced.todomvc;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.model.VehicleAppUser;
import com.dombi.advanced.todomvc.repository.TodoRepository;
import com.dombi.advanced.todomvc.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final TodoRepository todoRepository;

    private final UserRepository users;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(TodoRepository todoRepository, UserRepository users) {
        this.todoRepository = todoRepository;
        this.users = users;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) {
        log.debug("initializing sample data...");
        IntStream.range(0,3)
                .boxed()
                .map(integer -> Todo.builder()
                        .status(Status.ACTIVE)
                        .title((integer + 1) + ". TODO  item")
                        .build())
                .forEach(todoRepository::save);
        log.debug("printing all todos...");
        todoRepository.findAll().forEach(v -> log.debug(" Todo:" + v.toString()));

        users.save(VehicleAppUser.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER"))
            .build()
        );

        users.save(VehicleAppUser.builder()
            .username("admin")
            .password(passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );
        log.debug("printing all users...");
        users.findAll().forEach(v -> log.debug(" VehicleAppUser :" + v.toString()));
    }
}
