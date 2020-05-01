package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.UpdateAvatarApi
import kotlinx.coroutines.Deferred

interface UpdateAvatarRepository {
    suspend fun updateAvatarAsync(token: String, avatar: String): Deferred<UpdateAvatarApi>
    suspend fun updateAvatarInLocalStorage(avatar: String)
}