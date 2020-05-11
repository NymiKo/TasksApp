package com.easyprog.domain.repositories.implementations

import android.util.Log
import com.easyprog.data.remote.models.SendTasksListOnServerApi
import com.easyprog.data.remote.providers.SendTasksListOnServerProvider
import com.easyprog.data.remote.providers.implementations.SendTasksListOnServerProviderImpl
import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.domain.converters.TasksConverterImpl
import com.easyprog.domain.repositories.SendTasksListOnServerRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.RequestBody.Companion.toRequestBody

class SendTasksListOnServerRepositoryImpl(val sendTasksListOnServerProvider: SendTasksListOnServerProvider): SendTasksListOnServerRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun sendTasksAsync(tasksListEntity: List<TasksListEntity>, token: String): Deferred<MutableList<SendTasksListOnServerApi>> {
        return try {
            GlobalScope.async {
                val list = tasksListEntity.map {
                    TasksConverterImpl().fromEntityToTaskNotServer(model = it, token = token)
                }
                Log.e("TAG", list[0].toString())
                val requestBody = Gson().toJson(list).toRequestBody(null)
                sendTasksListOnServerProvider.sendTasks(requestBody = requestBody).await()
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}