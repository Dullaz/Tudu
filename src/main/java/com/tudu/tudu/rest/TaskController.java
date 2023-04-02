package com.tudu.tudu.rest;

import java.util.UUID;

import com.tudu.tudu.rest.dto.TaskDto;
import com.tudu.tudu.rest.exception.ResourceNotFoundException;
import com.tudu.tudu.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task-lists/{taskListId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@PathVariable UUID taskListId, @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskListId, taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskListId, id, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskListId, @PathVariable UUID id) {
        taskService.deleteTask(taskListId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID taskListId, @PathVariable UUID id) {
        taskService.completeTask(taskListId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}