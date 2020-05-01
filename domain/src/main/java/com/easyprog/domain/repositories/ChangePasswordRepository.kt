package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.ChangePasswordApi
import kotlinx.coroutines.Deferred

interface ChangePasswordRepository {
    suspend fun changePasswordAsync(token: String, oldPassword: String, newPassword: String): Deferred<ChangePasswordApi>
}