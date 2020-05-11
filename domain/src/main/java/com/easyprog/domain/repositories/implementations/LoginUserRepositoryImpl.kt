package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.LoginUserApi
import com.easyprog.data.remote.providers.LoginUserProvider
import com.easyprog.data.remote.providers.implementations.LoginUserProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.UserProfileEntity
import com.easyprog.domain.repositories.LoginUserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception
import javax.inject.Inject

class LoginUserRepositoryImpl(val loginUserProvider: LoginUserProvider): LoginUserRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun LoginUserAsync(login: String, password: String): Deferred<LoginUserApi> {

        return try {
            GlobalScope.async {
                loginUserProvider.LoginUser(login = login, password = password).await()
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun InsertUserDataAsync(login: String, password: String, avatar: String, name: String, surname: String, email: String) {
        val roomDatabase: RoomDatabaseApp? = null
        try {
            GlobalScope.async {
                val userProfileEntity = UserProfileEntity(id = 1, login = login, avatar = avatar, name = name,
                    surname = surname, email = email)
                roomDatabase!!.userProfileDao().insertUserProfile(userProfileEntity = userProfileEntity)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}