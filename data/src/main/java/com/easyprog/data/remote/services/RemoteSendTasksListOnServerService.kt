package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.SendTasksListOnServerApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteSendTasksListOnServerService {

    @POST("send_task.php")
    fun sendTasks(@Body requestBody: RequestBody): Deferred<MutableList<SendTasksListOnServerApi>>
}