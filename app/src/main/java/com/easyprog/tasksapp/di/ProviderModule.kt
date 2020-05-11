package com.easyprog.tasksapp.di

import com.easyprog.data.remote.providers.*
import com.easyprog.data.remote.providers.implementations.*
import com.easyprog.data.remote.services.*
import dagger.Module
import dagger.Provides

@Module
class ProviderModule {

    @Provides
    fun createLoginUserProvider(remoteLoginUserService: RemoteLoginUserService): LoginUserProvider {
        return LoginUserProviderImpl(remoteLoginUserService = remoteLoginUserService)
    }

    @Provides
    fun createAddedParticipantProvider(remoteAddedParticipantService: RemoteAddedParticipantService): AddedParticipantProvider {
        return AddedParticipantProviderImpl(remoteAddedParticipantService = remoteAddedParticipantService)
    }

    @Provides
    fun createChangePasswordProvider(remoteChangePasswordService: RemoteChangePasswordService): ChangePasswordProvider {
        return ChangePasswordProviderImpl(remoteChangePasswordService = remoteChangePasswordService)
    }

    @Provides
    fun createCreateTaskProvider(remoteCreateTaskService: RemoteCreateTaskService): CreateTaskProvider {
        return CreateTaskProviderImpl(remoteCreateTaskService = remoteCreateTaskService)
    }

    @Provides
    fun createEditPersonalDataProvider(remoteEditPersonalDataService: RemoteEditPersonalDataService): EditPersonalDataProvider {
        return EditPersonalDataProviderImpl(remoteEditPersonalDataService = remoteEditPersonalDataService)
    }

    @Provides
    fun createRegistrationUserProvider(remoteRegistrationUserService: RemoteRegistrationUserService): RegistrationUserProvider {
        return RegistrationUserProviderImpl(remoteRegistrationUserService = remoteRegistrationUserService)
    }

    @Provides
    fun createSendTasksListOnServerProvider(remoteSendTasksListOnServerService: RemoteSendTasksListOnServerService): SendTasksListOnServerProvider {
        return SendTasksListOnServerProviderImpl(remoteSendTasksListOnServerService = remoteSendTasksListOnServerService)
    }

    @Provides
    fun createUpdateAvatarProvider(remoteUpdateAvatarService: RemoteUpdateAvatarService): UpdateAvatarProvider {
        return UpdateAvatarProviderImpl(remoteUpdateAvatarService = remoteUpdateAvatarService)
    }
}