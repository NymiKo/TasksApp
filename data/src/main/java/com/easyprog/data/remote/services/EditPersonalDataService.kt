package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.EditPersonalDataApi
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EditPersonalDataService {

    @FormUrlEncoded
    @POST("edit_personal_data.php")
    fun editPersonalData(@Field("token") token: String,
                         @Field("name") name: String,
                         @Field("surname") surname: String,
                         @Field("email") email: String,
                         @Field("login") login: String): Deferred<EditPersonalDataApi>
}