package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.LoginUserApi
import com.easyprog.data.storage.model.UserProfileEntity
import kotlinx.coroutines.Deferred

interface LoginUserRepository {
    suspend fun LoginUserAsync(login: String, password: String): Deferred<LoginUserApi>
    suspend fun InsertUserDataAsync(login: String, password: String, name: String, avatar: String,
                                            surname: String, email: String)
}