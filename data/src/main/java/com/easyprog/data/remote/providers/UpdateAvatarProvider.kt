package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.UpdateAvatarApi
import kotlinx.coroutines.Deferred

interface UpdateAvatarProvider {

    fun updateAvatar(token: String, avatar: String): Deferred<UpdateAvatarApi>
}