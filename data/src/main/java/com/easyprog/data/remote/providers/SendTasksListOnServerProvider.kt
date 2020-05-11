package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.SendTasksListOnServerApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody

interface SendTasksListOnServerProvider {

    fun sendTasks(requestBody: RequestBody): Deferred<MutableList<SendTasksListOnServerApi>>
}