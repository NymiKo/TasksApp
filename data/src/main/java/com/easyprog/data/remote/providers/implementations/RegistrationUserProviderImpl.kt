package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.RegistrationUserApi
import com.easyprog.data.remote.providers.RegistrationUserProvider
import com.easyprog.data.remote.services.RemoteRegistrationUserService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class RegistrationUserProviderImpl(val remoteRegistrationUserService: RemoteRegistrationUserService): RegistrationUserProvider {
    @UnstableDefault
    override fun RegistrationUser(token: String, login: String, password: String, avatar: String,
                                  name: String, surname: String, email: String): Deferred<RegistrationUserApi> {
        return remoteRegistrationUserService.registerUser(token = token, login = login,
            password = password, avatar = avatar, name = name, surname = surname, email = email)
    }
}