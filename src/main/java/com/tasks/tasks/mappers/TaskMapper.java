package com.tasks.tasks.mappers;

import com.tasks.tasks.domain.dto.TaskDto;
import com.tasks.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
