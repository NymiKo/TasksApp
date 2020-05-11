package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.LoginUserApi
import kotlinx.coroutines.Deferred

interface LoginUserProvider {
    fun LoginUser(login: String, password: String): Deferred<LoginUserApi>
}