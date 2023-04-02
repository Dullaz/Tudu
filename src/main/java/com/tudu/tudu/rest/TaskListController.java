package com.tudu.tudu.rest;

import com.tudu.tudu.rest.dto.TaskListDto;
import com.tudu.tudu.rest.exception.ResourceNotFoundException;
import com.tudu.tudu.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {
    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskListDto createdTaskList = taskListService.createTaskList(taskListDto);
        return new ResponseEntity<>(createdTaskList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDto> getTaskListById(@PathVariable UUID id) {
        TaskListDto taskListDto = taskListService.getTaskListById(id);
        return new ResponseEntity<>(taskListDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getAllTaskLists() {
        List<TaskListDto> taskListsDto = taskListService.getAllTaskLists();
        return new ResponseEntity<>(taskListsDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDto> updateTaskList(@PathVariable UUID id, @RequestBody TaskListDto taskListDto) {
        TaskListDto updatedTaskList = taskListService.updateTaskList(id, taskListDto);
        return new ResponseEntity<>(updatedTaskList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable UUID id) {
        taskListService.deleteTaskList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}