package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.LoginUserApi
import com.easyprog.data.remote.providers.LoginUserProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.UserProfileEntity
import com.easyprog.domain.repositories.LoginUserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class LoginUserRepositoryImpl(val roomDatabase: RoomDatabaseApp): LoginUserRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun LoginUserAsync(login: String, password: String): Deferred<LoginUserApi> {
        val loginUserProviderImpl = LoginUserProviderImpl()

        return try {
            val loginProvider = loginUserProviderImpl.LoginUser(login = login, password = password).await()
            GlobalScope.async {
                loginProvider
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun InsertUserDataAsync(login: String, password: String, avatar: String, name: String, surname: String, email: String) {
        try {
            GlobalScope.async {
                val userProfileEntity = UserProfileEntity(id = 1, login = login, avatar = avatar, name = name,
                    surname = surname, email = email)
                roomDatabase.userProfileDao().insertUserProfile(userProfileEntity = userProfileEntity)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}