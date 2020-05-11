package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.CreateTaskApi
import com.easyprog.data.remote.providers.CreateTaskProvider
import com.easyprog.data.remote.services.RemoteCreateTaskService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import okhttp3.RequestBody

class CreateTaskProviderImpl(val remoteCreateTaskService: RemoteCreateTaskService): CreateTaskProvider {

    @UnstableDefault
    override fun createTask(requestBody: RequestBody): Deferred<CreateTaskApi> {
        return remoteCreateTaskService.createTask(requestBody = requestBody)
    }
}