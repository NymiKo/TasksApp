package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.AddedParticipantApi
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteAddedParticipantService {

    @FormUrlEncoded
    @POST("added_participant.php")
    fun addedParticipant(@Field("email") email: String): Deferred<AddedParticipantApi>
}