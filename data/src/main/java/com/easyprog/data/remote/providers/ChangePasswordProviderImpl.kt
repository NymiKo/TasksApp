package com.easyprog.data.remote.providers

import com.easyprog.data.remote.helpers.RetrofitFactory
import com.easyprog.data.remote.models.ChangePasswordApi
import com.easyprog.data.remote.services.ChangePasswordService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import okhttp3.RequestBody

class ChangePasswordProviderImpl {

    @UnstableDefault
    fun changePassword(requestBody: RequestBody): Deferred<ChangePasswordApi> {
        return RetrofitFactory.getRetrofitClient().create(ChangePasswordService::class.java)
            .changePassword(requestBody = requestBody)
    }
}