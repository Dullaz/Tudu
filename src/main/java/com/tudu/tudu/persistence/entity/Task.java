package com.tudu.tudu.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    private String description;
    private boolean completed;

    @ManyToOne
    private TaskList taskList;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }
}
