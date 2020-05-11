package com.easyprog.domain.repositories

import com.easyprog.data.storage.model.TasksListEntity

interface TasksNotServerRepository {
    fun getTasks(): List<TasksListEntity>
}