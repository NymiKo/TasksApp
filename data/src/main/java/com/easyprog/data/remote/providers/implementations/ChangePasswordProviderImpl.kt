package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.ChangePasswordApi
import com.easyprog.data.remote.providers.ChangePasswordProvider
import com.easyprog.data.remote.services.RemoteChangePasswordService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import okhttp3.RequestBody

class ChangePasswordProviderImpl(val remoteChangePasswordService: RemoteChangePasswordService): ChangePasswordProvider {

    @UnstableDefault
    override fun changePassword(requestBody: RequestBody): Deferred<ChangePasswordApi> {
        return remoteChangePasswordService.changePassword(requestBody = requestBody)
    }
}