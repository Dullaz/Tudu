package com.tudu.tudu.service;

import com.tudu.tudu.persistence.entity.TaskList;
import com.tudu.tudu.persistence.repository.TaskListRepository;
import com.tudu.tudu.rest.dto.TaskListDto;
import com.tudu.tudu.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskListService {
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskListDto createTaskList(TaskListDto taskListDto) {
        TaskList taskList = new TaskList(taskListDto.getName());
        taskListRepository.save(taskList);

        return TaskListDto.fromEntity(taskList);
    }

    public TaskListDto getTaskListById(UUID id) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        if (taskListOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task list not found with id " + id);
        }

        TaskList taskList = taskListOptional.get();
        return TaskListDto.fromEntity(taskList);
    }

    public List<TaskListDto> getAllTaskLists() {
        List<TaskList> taskLists = taskListRepository.findAll();
        return taskLists.stream().map(TaskListDto::fromEntity).collect(Collectors.toList());
    }

    public TaskListDto updateTaskList(UUID id, TaskListDto taskListDto) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        if (taskListOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task list not found with id " + id);
        }

        TaskList taskList = taskListOptional.get();
        taskList.setName(taskListDto.getName());
        taskListRepository.save(taskList);

        return TaskListDto.fromEntity(taskList);
    }

    public void deleteTaskList(UUID id) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(id);
        if (taskListOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task list not found with id " + id);
        }

        TaskList taskList = taskListOptional.get();
        taskListRepository.delete(taskList);
    }

}