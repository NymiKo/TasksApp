package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.RegistrationUserApi
import com.easyprog.data.remote.providers.RegistrationUserProvider
import com.easyprog.data.remote.providers.implementations.RegistrationUserProviderImpl
import com.easyprog.domain.repositories.RegistrationUserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class RegistrationUserRepositoryImpl(val registrationUserProvider: RegistrationUserProvider): RegistrationUserRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun RegistrationUserAsync(token: String, login: String, password: String, avatar: String, name: String,
                                               surname: String, email: String): Deferred<RegistrationUserApi> {

        return try {
            val registrationProvider = registrationUserProvider
                .RegistrationUser(token = token, login = login, password = password, avatar = avatar,
                    name = name, surname = surname, email = email).await()
            GlobalScope.async {
                registrationProvider
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}