package com.tasks.tasks.mappers;

import com.tasks.tasks.domain.dto.TaskListDto;
import com.tasks.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
