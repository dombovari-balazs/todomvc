package com.dombi.advanced.todomvc.controller;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.repository.TodoDao;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String deleteCompleted(){
        TodoDao.removeCompleted();
        return SUCCESS;
    }

}
