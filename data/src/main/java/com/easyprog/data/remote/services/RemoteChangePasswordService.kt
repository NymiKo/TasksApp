package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.ChangePasswordApi
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteChangePasswordService {

    @POST("change_password.php")
    fun changePassword(@Body requestBody: RequestBody): Deferred<ChangePasswordApi>
}