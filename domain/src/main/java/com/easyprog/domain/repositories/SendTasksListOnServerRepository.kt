package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.SendTasksListOnServerApi
import com.easyprog.data.storage.model.TasksListEntity
import kotlinx.coroutines.Deferred

interface SendTasksListOnServerRepository {

    fun sendTasksAsync(tasksListEntity: List<TasksListEntity>, token: String): Deferred<MutableList<SendTasksListOnServerApi>>
}