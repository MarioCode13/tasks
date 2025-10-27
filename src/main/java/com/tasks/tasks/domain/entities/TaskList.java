package com.tasks.tasks.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name= "task_lists")
public class TaskList {
    private UUID id;
}
