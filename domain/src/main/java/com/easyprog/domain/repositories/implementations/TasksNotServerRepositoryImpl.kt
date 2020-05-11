package com.easyprog.domain.repositories.implementations

import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.domain.repositories.TasksNotServerRepository

class TasksNotServerRepositoryImpl(val roomDatabase: RoomDatabaseApp): TasksNotServerRepository {

    override fun getTasks(): List<TasksListEntity> {
        return try {
            roomDatabase.tasksListDao().getTasksNotServer(on_server = false)
        } catch (e: Exception) {
            error(e)
        }
    }

}