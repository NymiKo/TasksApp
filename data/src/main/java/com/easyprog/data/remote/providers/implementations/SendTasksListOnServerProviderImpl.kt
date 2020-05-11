package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.SendTasksListOnServerApi
import com.easyprog.data.remote.providers.SendTasksListOnServerProvider
import com.easyprog.data.remote.services.RemoteSendTasksListOnServerService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import okhttp3.RequestBody

class SendTasksListOnServerProviderImpl(val remoteSendTasksListOnServerService: RemoteSendTasksListOnServerService): SendTasksListOnServerProvider {

    @UnstableDefault
    override fun sendTasks(requestBody: RequestBody): Deferred<MutableList<SendTasksListOnServerApi>> {
        return remoteSendTasksListOnServerService.sendTasks(requestBody = requestBody)
    }
}