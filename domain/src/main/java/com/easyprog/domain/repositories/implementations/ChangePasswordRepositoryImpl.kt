package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.ChangePasswordApi
import com.easyprog.data.remote.providers.ChangePasswordProvider
import com.easyprog.data.remote.providers.implementations.ChangePasswordProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.domain.models.ChangePassword
import com.easyprog.domain.repositories.ChangePasswordRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.RequestBody.Companion.toRequestBody

class ChangePasswordRepositoryImpl(val changePasswordProvider: ChangePasswordProvider): ChangePasswordRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun changePasswordAsync(token: String, oldPassword: String, newPassword: String): Deferred<ChangePasswordApi> {

        return try {
            GlobalScope.async {
                val requestBody = Gson().toJson(ChangePassword(token = token, oldPassword = oldPassword, newPassword = newPassword))
                    .toRequestBody(null)
                changePasswordProvider.changePassword(requestBody = requestBody).await()
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}