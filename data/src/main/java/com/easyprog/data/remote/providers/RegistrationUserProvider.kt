package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.RegistrationUserApi
import kotlinx.coroutines.Deferred

interface RegistrationUserProvider {

    fun RegistrationUser(token: String, login: String, password: String, avatar: String,
                         name: String, surname: String, email: String): Deferred<RegistrationUserApi>
}