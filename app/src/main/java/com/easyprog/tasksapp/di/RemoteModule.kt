package com.easyprog.tasksapp.di

import com.easyprog.data.remote.services.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @UnstableDefault
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://a0389729.xsph.ru/TaskApp/")
        .addConverterFactory(Json.nonstrict.asConverterFactory(contentType = "application/json".toMediaTypeOrNull()!!))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideRemoteLoginUserService(retrofit: Retrofit): RemoteLoginUserService =
        retrofit.create(RemoteLoginUserService::class.java)

    @Provides
    @Singleton
    fun provideRemoteAddedParticipantService(retrofit: Retrofit): RemoteAddedParticipantService =
        retrofit.create(RemoteAddedParticipantService::class.java)

    @Provides
    @Singleton
    fun provideRemoteChangePasswordService(retrofit: Retrofit): RemoteChangePasswordService =
        retrofit.create(RemoteChangePasswordService::class.java)

    @Provides
    @Singleton
    fun provideRemoteCreateTaskService(retrofit: Retrofit): RemoteCreateTaskService =
        retrofit.create(RemoteCreateTaskService::class.java)

    @Provides
    @Singleton
    fun provideRemoteEditPersonalService(retrofit: Retrofit): RemoteEditPersonalDataService =
        retrofit.create(RemoteEditPersonalDataService::class.java)

    @Provides
    fun provideRemoteRegistrationUserService(retrofit: Retrofit): RemoteRegistrationUserService =
        retrofit.create(RemoteRegistrationUserService::class.java)

    @Provides
    fun provideRemoteSendTasksListOnServerService(retrofit: Retrofit): RemoteSendTasksListOnServerService =
        retrofit.create(RemoteSendTasksListOnServerService::class.java)

    @Provides
    fun provideRemoteUpdateAvatarService(retrofit: Retrofit): RemoteUpdateAvatarService =
        retrofit.create(RemoteUpdateAvatarService::class.java)
}