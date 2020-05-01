package com.easyprog.domain.repositories

import android.net.Uri
import com.easyprog.data.remote.models.RegistrationUserApi
import com.easyprog.data.storage.model.UserProfileEntity
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody

interface RegistrationUserRepository {
    suspend fun RegistrationUserAsync(token: String, login: String, password: String, avatar: String, name: String,
                                      surname: String, email: String): Deferred<RegistrationUserApi>
}