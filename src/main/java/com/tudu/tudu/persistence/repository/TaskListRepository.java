package com.tudu.tudu.persistence.repository;

import com.tudu.tudu.persistence.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
