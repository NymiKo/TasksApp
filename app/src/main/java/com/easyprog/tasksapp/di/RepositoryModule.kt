package com.easyprog.tasksapp.di

import android.arch.persistence.room.RoomDatabase
import com.easyprog.data.remote.providers.*
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.domain.repositories.*
import com.easyprog.domain.repositories.implementations.*
import com.easyprog.tasksapp.App
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideLoginUserRepository(loginUserProvider: LoginUserProvider): LoginUserRepository {
        return LoginUserRepositoryImpl(loginUserProvider = loginUserProvider)
    }

    @Provides
    fun provideAddedParticipantRepository(addedParticipantProvider: AddedParticipantProvider): AddedParticipantRepository {
        return AddedParticipantRepositoryImpl(addedParticipantProvider = addedParticipantProvider)
    }

    @Provides
    fun provideChangePasswordRepository(changePasswordProvider: ChangePasswordProvider): ChangePasswordRepository {
        return ChangePasswordRepositoryImpl(changePasswordProvider = changePasswordProvider)
    }

    @Provides
    fun provideCreateTaskRepository(createTaskProvider: CreateTaskProvider): CreateTaskRepository {
        return CreateTaskRepositoryImpl(createTaskProvider = createTaskProvider)
    }

    @Provides
    fun provideEditPersonalDataRepository(editPersonalDataProvider: EditPersonalDataProvider, roomDatabase: RoomDatabaseApp): EditPersonalDataRepository {
        return EditPersonalDataRepositoryImpl(editPersonalDataProvider = editPersonalDataProvider,
        roomDatabase = roomDatabase)
    }

    @Provides
    fun provideRegistrationUserRepository(registrationUserProvider: RegistrationUserProvider): RegistrationUserRepository {
        return RegistrationUserRepositoryImpl(registrationUserProvider = registrationUserProvider)
    }

    @Provides
    fun provideSendTasksListOnServerRepository(sendTasksListOnServerProvider: SendTasksListOnServerProvider): SendTasksListOnServerRepository {
        return SendTasksListOnServerRepositoryImpl(sendTasksListOnServerProvider = sendTasksListOnServerProvider)
    }

    @Provides
    fun provideUpdateAvatarRepository(updateAvatarProvider: UpdateAvatarProvider): UpdateAvatarRepository {
        return UpdateAvatarRepositoryImpl(updateAvatarProvider = updateAvatarProvider)
    }

    @Provides
    fun provideProfileRepository(roomDatabase: RoomDatabaseApp): ProfileRepository {
        return ProfileRepositoryImpl(roomDatabase = roomDatabase)
    }

    @Provides
    fun provideTasksNotServerRepository(roomDatabase: RoomDatabaseApp): TasksNotServerRepository {
        return TasksNotServerRepositoryImpl(roomDatabase = roomDatabase)
    }
}