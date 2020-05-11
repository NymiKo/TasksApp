package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.CreateTaskApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody

interface CreateTaskProvider {

    fun createTask(requestBody: RequestBody): Deferred<CreateTaskApi>
}