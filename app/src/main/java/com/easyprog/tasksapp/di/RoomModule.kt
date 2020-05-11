package com.easyprog.tasksapp.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.contract.RoomContract
import com.easyprog.data.storage.dao.TasksListDao
import com.easyprog.data.storage.helpers.MigrationHelper
import com.easyprog.tasksapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    fun provideRoom(): RoomDatabaseApp = Room.databaseBuilder(
        App.context, RoomDatabaseApp::class.java, RoomContract.databaseApp
    ).addMigrations(MigrationHelper().migration_1_2)
        .build()

//    @Provides
//    fun provideTasksListDao(roomDatabase: RoomDatabaseApp): TasksListDao =
//        roomDatabase.tasksListDao()
}