package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.UpdateAvatarApi
import com.easyprog.data.remote.providers.UpdateAvatarProvider
import com.easyprog.data.remote.services.RemoteUpdateAvatarService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class UpdateAvatarProviderImpl(val remoteUpdateAvatarService: RemoteUpdateAvatarService): UpdateAvatarProvider {

    @UnstableDefault
    override fun updateAvatar(token: String, avatar: String): Deferred<UpdateAvatarApi> {
        return remoteUpdateAvatarService.updateAvatar(token = token, avatar = avatar)
    }
}