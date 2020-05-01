package com.easyprog.domain.repositories.implementations

import android.media.Image
import android.net.Uri
import android.util.Log
import com.easyprog.data.remote.models.RegistrationUserApi
import com.easyprog.data.remote.providers.RegistrationUserProviderImpl
import com.easyprog.data.storage.model.UserProfileEntity
import com.easyprog.domain.models.User
import com.easyprog.domain.repositories.RegistrationUserRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.lang.Exception
import java.net.URI

class RegistrationUserRepositoryImpl: RegistrationUserRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun RegistrationUserAsync(token: String, login: String, password: String, avatar: String, name: String,
                                               surname: String, email: String): Deferred<RegistrationUserApi> {
        val registrationProviderImpl = RegistrationUserProviderImpl()
        return try {
            val registrationProvider = registrationProviderImpl
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