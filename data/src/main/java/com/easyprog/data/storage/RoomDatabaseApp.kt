package com.easyprog.data.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.easyprog.data.storage.contract.RoomContract
import com.easyprog.data.storage.dao.UserProfileDao
import com.easyprog.data.storage.model.UserProfileEntity

@Database(entities = [UserProfileEntity::class], version = 1)
abstract class RoomDatabaseApp: RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    companion object {
        fun buildDataSource(context: Context): RoomDatabaseApp = Room.databaseBuilder(
            context, RoomDatabaseApp::class.java, RoomContract.databaseApp
        ).build()
    }
}