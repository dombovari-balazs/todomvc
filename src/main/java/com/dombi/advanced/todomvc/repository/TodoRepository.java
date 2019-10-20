package com.dombi.advanced.todomvc.repository;

import com.dombi.advanced.todomvc.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
