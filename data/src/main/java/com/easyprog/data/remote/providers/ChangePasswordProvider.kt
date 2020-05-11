package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.ChangePasswordApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody

interface ChangePasswordProvider {

    fun changePassword(requestBody: RequestBody): Deferred<ChangePasswordApi>
}