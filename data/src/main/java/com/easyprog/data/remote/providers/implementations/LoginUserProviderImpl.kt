package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.LoginUserApi
import com.easyprog.data.remote.providers.LoginUserProvider
import com.easyprog.data.remote.services.RemoteLoginUserService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class LoginUserProviderImpl(val remoteLoginUserService: RemoteLoginUserService): LoginUserProvider {

    @UnstableDefault
    override fun LoginUser(login: String, password: String): Deferred<LoginUserApi> {
        return remoteLoginUserService.loginUser(login = login, password = password)
    }
}