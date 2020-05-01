package com.easyprog.data.remote.providers

import com.easyprog.data.remote.helpers.RetrofitFactory
import com.easyprog.data.remote.models.UpdateAvatarApi
import com.easyprog.data.remote.services.UpdateAvatarService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class UpdateAvatarProviderImpl {

    @UnstableDefault
    fun updateAvatar(token: String, avatar: String): Deferred<UpdateAvatarApi> {
        return RetrofitFactory.getRetrofitClient().create(UpdateAvatarService::class.java)
            .updateAvatar(token = token, avatar = avatar)
    }
}