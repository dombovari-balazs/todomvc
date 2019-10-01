package com.dombi.advanced.todomvc.controller;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoDao;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TodoController {

    private static final String SUCCESS = "{\"success\":true}";
    private int counter = 3; //todo

    // add new
    @PostMapping("/addTodo")
    public String addNew(@RequestParam HashMap<String,String> map){
        Todo newTodo = new Todo((counter + ""),map.get("todo-title"), Status.ACTIVE); // csöves megoldás JPA-ig todo
        TodoDao.add(newTodo);
        return SUCCESS;
    }

    // list by id(?) --> or rather status?:D
    @PostMapping("/list")
    public List<Todo> listById(){
        return TodoDao.all();
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




}
