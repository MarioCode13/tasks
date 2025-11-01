package com.tasks.tasks.services.impl;

import com.tasks.tasks.domain.entities.TaskList;
import com.tasks.tasks.repositories.TaskListRepository;
import com.tasks.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId()) {
            throw new IllegalArgumentException("Task list already has an ID");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title cannot be empty");
        }
        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()) {
            throw new IllegalArgumentException("Task list must contain id");
        }
        // check that task list id doesn't match passed id
        if(!Objects.equals(taskList.getId(), taskListId)) {
             throw new IllegalArgumentException("Task list id change not permitted");
        }

        TaskList existingTaskList = taskListRepository.findById(taskListId)
               .orElseThrow(() ->
                       new IllegalArgumentException("Task list with id " + taskListId + " not found"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());

        return taskListRepository.save(existingTaskList);
    }
}
