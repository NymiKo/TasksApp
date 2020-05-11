package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.CreateTaskApi
import com.easyprog.data.remote.providers.CreateTaskProvider
import com.easyprog.data.remote.providers.implementations.CreateTaskProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.domain.models.CreateTaskForParticipant
import com.easyprog.domain.repositories.CreateTaskRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.Exception

class CreateTaskRepositoryImpl(val createTaskProvider: CreateTaskProvider): CreateTaskRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun createTaskAsync(name: String, description: String, color: String, end_date: String,
                                 type: String, creator: String, participants: MutableList<String>?): Deferred<CreateTaskApi> {
        return try {
            GlobalScope.async {
                val requestBody = Gson().toJson(CreateTaskForParticipant(name = name, description = description, color = color,
                    end_date = end_date, type = type, creator = creator, participants = participants)).toRequestBody(null)
                createTaskProvider.createTask(requestBody = requestBody).await()
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override fun insertTaskLocal(id_on_server: Int, name: String, description: String, color: String, end_date: String, type: String,
                                 on_server: Boolean, creator: Boolean){
        try {
            GlobalScope.async {
                val roomDatabase: RoomDatabaseApp? = null
                val insertTaskEntity = TasksListEntity(id_on_server = id_on_server, name = name, description = description, color = color,
                    end_date = end_date, type = type, on_server = on_server, creator = creator)

                roomDatabase?.tasksListDao()?.insertTask(tasksListEntity = insertTaskEntity)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }


}