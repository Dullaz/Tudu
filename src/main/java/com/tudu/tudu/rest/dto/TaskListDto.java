package com.tudu.tudu.rest.dto;

import com.tudu.tudu.persistence.entity.TaskList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TaskListDto {
    private UUID id;
    private String name;
    private List<TaskDto> tasks = new ArrayList<>();

    public TaskListDto(String name) {
        this.name = name;
    }

    public static TaskListDto fromEntity(TaskList taskList) {
        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setId(taskList.getId());
        taskListDto.setName(taskList.getName());
        taskListDto.setTasks(taskList.getTasks().stream().map(TaskDto::fromEntity).collect(Collectors.toList()));
        return taskListDto;
    }

    public static TaskList toEntity(TaskListDto taskListDto) {
        TaskList taskList = new TaskList();
        taskList.setId(taskListDto.getId());
        taskList.setName(taskListDto.getName());
        taskList.setTasks(taskListDto.getTasks().stream().map(TaskDto::toEntity).collect(Collectors.toList()));
        return taskList;
    }
}