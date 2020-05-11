package com.easyprog.data.remote.services

import com.easyprog.data.remote.models.LoginUserApi
import dagger.Provides
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface RemoteLoginUserService {

    @GET("login.php")
    fun loginUser(@Query("login") login: String,
                  @Query("password") password: String): Deferred<LoginUserApi>
}