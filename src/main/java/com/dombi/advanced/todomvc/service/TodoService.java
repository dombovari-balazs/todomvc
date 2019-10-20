package com.dombi.advanced.todomvc.service;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteCompleted() {
        List<Todo> allByCompletedIsTrue = todoRepository.getAllComplete();
        todoRepository.deleteAll(allByCompletedIsTrue);
    }

    public void toggleAll(boolean complete) {
        List<Todo> all = todoRepository.findAll();
        all.forEach(t ->t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
        todoRepository.saveAll(all);
    }

    public void remove(Long id) {
        todoRepository.deleteById(id);
    }

    public void update(Long id, String title) {
        Todo one = todoRepository.getOne(id);
        one.setTitle(title);
        todoRepository.save(one);
    }

    public Todo find(Long id) {
        return todoRepository.getOne(id);
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo todo = todoRepository.getOne(id);
        if (isComplete) {
            todo.setStatus(Status.COMPLETE);
        } else {
            todo.setStatus(Status.ACTIVE);
        }
        todoRepository.save(todo);

    }

    public List<Todo> ofStatus(String statusString) {
        List<Todo> DATA = todoRepository.findAll();
        return (statusString == null || statusString.isEmpty()) ? DATA : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public List<Todo> ofStatus(Status status) {
        List<Todo> DATA = todoRepository.findAll();

        return DATA.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }
}
