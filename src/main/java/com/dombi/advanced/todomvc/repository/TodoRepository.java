package com.dombi.advanced.todomvc.repository;

import com.dombi.advanced.todomvc.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Query("SELECT t FROM Todo  t WHERE t.status = 'COMPLETE' ")
    List<Todo> getAllComplete() ;

}
