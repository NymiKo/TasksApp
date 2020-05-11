package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.CreateTaskApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteCreateTaskService {

    @POST("create_task.php")
    fun createTask(@Body requestBody: RequestBody): Deferred<CreateTaskApi>
}