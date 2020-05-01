package com.easyprog.data.remote.providers

import com.easyprog.data.remote.helpers.RetrofitFactory
import com.easyprog.data.remote.models.RegistrationUserApi
import com.easyprog.data.remote.services.RegistrationUserService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RegistrationUserProviderImpl {
    @UnstableDefault
    fun RegistrationUser(token: String, login: String, password: String, avatar: String,
            name: String, surname: String, email: String): Deferred<RegistrationUserApi> {
        return RetrofitFactory.getRetrofitClient().create(RegistrationUserService::class.java)
            .registerUser(token = token, login = login, password = password, avatar = avatar,
                    name = name, surname = surname, email = email)
    }
}