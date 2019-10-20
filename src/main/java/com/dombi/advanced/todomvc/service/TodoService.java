package com.dombi.advanced.todomvc.service;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoDao;
import com.dombi.advanced.todomvc.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;

    public void addNew(String s, Status active){
        Todo newTodo = Todo.builder()
                .title(s)
                .status(active)
                .build();
        todoRepository.save(newTodo);
    }


    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }
}
