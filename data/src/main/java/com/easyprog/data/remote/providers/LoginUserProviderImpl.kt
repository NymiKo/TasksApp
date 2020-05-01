package com.easyprog.data.remote.providers

import com.easyprog.data.remote.helpers.RetrofitFactory
import com.easyprog.data.remote.models.LoginUserApi
import com.easyprog.data.remote.services.LoginUserService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class LoginUserProviderImpl {

    @UnstableDefault
    fun LoginUser(login: String, password: String): Deferred<LoginUserApi> {
        return RetrofitFactory.getRetrofitClient().create(LoginUserService::class.java)
            .loginUser(login = login, password = password)
    }
}