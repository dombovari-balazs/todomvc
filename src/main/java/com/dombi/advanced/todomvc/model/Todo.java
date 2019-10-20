package com.dombi.advanced.todomvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;


    public boolean isCompleted() {
        return this.status == Status.COMPLETE;
    }


}
