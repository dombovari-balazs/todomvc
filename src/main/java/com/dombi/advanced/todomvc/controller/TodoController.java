package com.dombi.advanced.todomvc.controller;

import com.dombi.advanced.todomvc.model.Status;
import com.dombi.advanced.todomvc.model.Todo;
import com.dombi.advanced.todomvc.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class TodoController {
    private TodoService todoService;

    private static final String SUCCESS = "{\"success\":true}";

    // add new
    @PostMapping("/addTodo")
    public String addNew(@RequestParam HashMap<String,String> map){
        todoService.addNew(map.get("todo-title"), Status.ACTIVE);
        return SUCCESS;
    }

    // list by id(?) --> or rather status?:D
    @PostMapping("/list")
    public List<Todo> listById(@RequestParam Map<String,String> map){
        System.out.println(map.get("status"));
        return todoService.ofStatus(map.get("status"));

    }

    // Remove all completed
    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        todoService.deleteCompleted();
        return SUCCESS;
    }

    // Toggle all status
    @PutMapping("/todos/toggle_all")
    public String toggleAll(@RequestParam HashMap<String,String> map){
        String complete = map.get("toggle-all");
        todoService.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    // Remove by id
    @DeleteMapping("/todos/{id}")
    public String deleteById(@PathVariable Long id){
        todoService.remove(id);
        return SUCCESS;
    }

    // Update by id
    @PutMapping("/todos/{id}")
    public String updateById(@PathVariable Long id, @RequestParam HashMap<String,String> map ){
        todoService.update(id, map.get("todo-title"));
        return SUCCESS;
    }

    // Find by id
    @GetMapping("/todos/{id}")
    public Todo findById(@PathVariable Long id){
        return todoService.find(id);
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable Long id, @RequestParam HashMap<String,String> map ){
        boolean completed = map.get("status").equals("true");
        todoService.toggleStatus(id, completed);
        return SUCCESS;
    }


}
