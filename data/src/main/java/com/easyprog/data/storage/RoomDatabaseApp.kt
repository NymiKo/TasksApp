package com.easyprog.data.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.easyprog.data.storage.contract.RoomContract
import com.easyprog.data.storage.dao.TasksListDao
import com.easyprog.data.storage.dao.UserProfileDao
import com.easyprog.data.storage.helpers.MigrationHelper
import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.data.storage.model.UserProfileEntity

@Database(entities = [UserProfileEntity::class, TasksListEntity::class], version = 2)
abstract class RoomDatabaseApp: RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    abstract fun tasksListDao(): TasksListDao

}