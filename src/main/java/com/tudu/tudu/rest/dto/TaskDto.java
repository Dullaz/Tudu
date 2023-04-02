package com.tudu.tudu.rest.dto;

import java.util.UUID;

import com.tudu.tudu.persistence.entity.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String description;
    private boolean completed;

    public TaskDto(String description) {
        this.description = description;
        this.completed = false;
    }

    public static TaskDto fromEntity(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.isCompleted());
        return taskDto;
    }

    public static Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());
        return task;
    }
}