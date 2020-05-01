package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.UpdateAvatarApi
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UpdateAvatarService {
    @FormUrlEncoded
    @POST("update_avatar.php")
    fun updateAvatar(@Field("token") token: String,
                     @Field("avatar") avatar: String): Deferred<UpdateAvatarApi>

}