package com.dombi.advanced.todomvc.controller;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoDao;
import com.dombi.advanced.todomvc.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
public class TodoController {
    private TodoService todoService

    private static final String SUCCESS = "{\"success\":true}";
    private int counter = 3; //todo

    // add new
    @PostMapping("/addTodo")
    public String addNew(@RequestParam HashMap<String,String> map){
        todoService.addNew(map.get("todo-title"), Status.ACTIVE);
        return SUCCESS;
    }

    // list by id(?) --> or rather status?:D
    @PostMapping("/list")
    public List<Todo> listById(){
        return todoService.getAllTodo();
    }

    // Remove all completed
    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        TodoDao.removeCompleted();
        return SUCCESS;
    }

    // Toggle all status
    @PutMapping("/todos/toggle_all")
    public String toggleAll(@RequestParam HashMap<String,String> map){
        String complete = map.get("toggle-all");
        TodoDao.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    // Remove by id
    @DeleteMapping("/todos/{id}")
    public String deleteById(@PathVariable String id){
        TodoDao.remove(id);
        return SUCCESS;
    }

    // Update by id
    @PutMapping("/todos/{id}")
    public String updateById(@PathVariable String id, @RequestParam HashMap<String,String> map ){
        TodoDao.update(id, map.get("todo-title"));
        return SUCCESS;
    }

    // Find by id
    @GetMapping("/todos/{id}")
    public Todo findById(@PathVariable String id){
        return TodoDao.find(id);
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable String id, @RequestParam HashMap<String,String> map ){
        boolean completed = map.get("status").equals("true");
        TodoDao.toggleStatus(id, completed);
        return SUCCESS;
    }


}
