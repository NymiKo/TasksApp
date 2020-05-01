package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.RegistrationUserApi
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RegistrationUserService {
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(@Field("token") token: String,
                     @Field("login") login: String,
                     @Field("password") password: String,
                     @Field("avatar") avatar: String,
                     @Field("name") name: String,
                     @Field("surname") surname: String,
                     @Field("email") email: String): Deferred<RegistrationUserApi>
}