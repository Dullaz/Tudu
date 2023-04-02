package com.tudu.tudu.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.tudu.tudu.persistence.entity.TaskList;
import com.tudu.tudu.persistence.repository.TaskListRepository;
import com.tudu.tudu.persistence.repository.TaskRepository;
import com.tudu.tudu.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tudu.tudu.rest.dto.TaskDto;
import com.tudu.tudu.persistence.entity.Task;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    public TaskDto createTask(UUID taskListId, TaskDto taskDto) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task list not found with id " + taskListId);
        }

        TaskList taskList = taskListOptional.get();
        Task task = new Task(taskDto.getDescription());
        task.setTaskList(taskList);

        taskRepository.save(task);

        return TaskDto.fromEntity(task);
    }

    public TaskDto updateTask(UUID taskListId, UUID id, TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }

        Task task = taskOptional.get();
        if (!task.getTaskList().getId().equals(taskListId)) {
            throw new ResourceNotFoundException("Task not found in task list with id " + taskListId);
        }

        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isCompleted());

        taskRepository.save(task);

        return TaskDto.fromEntity(task);
    }

    public void deleteTask(UUID taskListId, UUID id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }

        Task task = taskOptional.get();
        if (!task.getTaskList().getId().equals(taskListId)) {
            throw new ResourceNotFoundException("Task not found in task list with id " + taskListId);
        }

        taskRepository.delete(task);
    }

    public void completeTask(UUID taskListId, UUID id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }

        Task task = taskOptional.get();
        if (!task.getTaskList().getId().equals(taskListId)) {
            throw new ResourceNotFoundException("Task not found in task list with id " + taskListId);
        }

        task.setCompleted(true);

        taskRepository.save(task);
    }

    public List<TaskDto> getTasksByTaskListId(UUID taskListId) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isEmpty()) {
            throw new ResourceNotFoundException("Task list not found with id " + taskListId);
        }

        TaskList taskList = taskListOptional.get();
        List<Task> tasks = taskList.getTasks();
        return tasks.stream().map(TaskDto::fromEntity).collect(Collectors.toList());
    }

}
