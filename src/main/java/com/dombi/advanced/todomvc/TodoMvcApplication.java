package com.dombi.advanced.todomvc;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class TodoMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(){
        return args -> {
            IntStream.range(0,3)
                    .boxed()
                    .map(integer -> Todo.builder()
                            .id(integer.toString())
                            .status(Status.ACTIVE)
                            .title((integer + 1) + ". TODO  item")
                            .build())
                    .forEach(TodoDao::add);


        };
    }

}
