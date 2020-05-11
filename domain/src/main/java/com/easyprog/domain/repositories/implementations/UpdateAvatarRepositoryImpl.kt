package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.UpdateAvatarApi
import com.easyprog.data.remote.providers.UpdateAvatarProvider
import com.easyprog.data.remote.providers.implementations.UpdateAvatarProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.domain.repositories.UpdateAvatarRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.Exception

class UpdateAvatarRepositoryImpl(val updateAvatarProvider: UpdateAvatarProvider): UpdateAvatarRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun updateAvatarAsync(token: String, avatar: String): Deferred<UpdateAvatarApi> {

        return try {
            val updateAvatarProvider = updateAvatarProvider.updateAvatar(token = token, avatar = avatar).await()
            GlobalScope.async {
                updateAvatarProvider
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun updateAvatarInLocalStorage(avatar: String) {
        try {
            GlobalScope.async {
                val roomDatabase: RoomDatabaseApp? = null
                roomDatabase?.userProfileDao()!!.updateAvatar(avatar = avatar)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}