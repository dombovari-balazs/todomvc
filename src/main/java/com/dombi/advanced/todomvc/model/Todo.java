package com.dombi.advanced.todomvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    private String title;
    private String id;
    private Status status;


    public boolean isCompleted() {
        return this.status == Status.COMPLETE;
    }


}
