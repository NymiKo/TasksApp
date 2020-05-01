package com.easyprog.tasksapp.di

import android.app.Application
import android.arch.persistence.room.RoomDatabase
import com.easyprog.data.storage.RoomDatabaseApp

class App: Application() {

    companion object {
        lateinit var roomDatabase: RoomDatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase = RoomDatabaseApp.buildDataSource(context = applicationContext)
    }
}