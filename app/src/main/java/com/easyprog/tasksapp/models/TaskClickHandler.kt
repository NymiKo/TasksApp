package com.easyprog.tasksapp.models

import com.easyprog.domain.models.Tasks

interface TaskClickHandler {
    fun onItemClick(item: Tasks, id: Int)
}