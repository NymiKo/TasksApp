package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.CreateTaskApi
import com.easyprog.data.storage.model.TasksListEntity
import kotlinx.coroutines.Deferred

interface CreateTaskRepository {
    fun createTaskAsync(name: String, description: String, color: String, end_date: String,
                        type: String, creator: String, participants: MutableList<String>?): Deferred<CreateTaskApi>

    fun insertTaskLocal(id_on_server: Int, name: String, description: String, color: String,
                        end_date: String, type: String, on_server: Boolean, creator: Boolean)
}