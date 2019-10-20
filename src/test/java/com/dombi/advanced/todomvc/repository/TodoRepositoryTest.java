package com.dombi.advanced.todomvc.repository;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;
    @org.junit.jupiter.api.Test
    void getAllByCompletedIsTrue() {
        Todo asd = Todo.builder().status(Status.ACTIVE).title("sadf").build();
        Todo asd2 = Todo.builder().status(Status.COMPLETE).build();
        Todo asd3 = Todo.builder().status(Status.COMPLETE).build();
        Todo asd4 = Todo.builder().status(Status.ACTIVE).build();
        todoRepository.save(asd);
        todoRepository.save(asd2);
        todoRepository.save(asd3);
        todoRepository.save(asd4);
        List<Todo> allByCompletedIsTrue = todoRepository.getAllComplete();
        System.out.println(allByCompletedIsTrue);
    }
}