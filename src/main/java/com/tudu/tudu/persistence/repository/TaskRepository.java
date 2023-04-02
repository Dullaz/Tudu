package com.tudu.tudu.persistence.repository;

import com.tudu.tudu.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
