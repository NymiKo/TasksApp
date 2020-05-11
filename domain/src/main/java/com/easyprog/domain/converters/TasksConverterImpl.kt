package com.easyprog.domain.converters

import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.domain.models.CreateTaskForParticipant
import com.easyprog.domain.models.TasksNotServer

class TasksConverterImpl {

    fun fromEntityToTaskNotServer(model: TasksListEntity, token: String): TasksNotServer{
        return TasksNotServer(name = model.name, description = model.description, color = model.color,
            end_date = model.end_date, type = model.type, creator = token)
    }
}