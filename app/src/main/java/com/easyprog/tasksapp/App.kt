package com.easyprog.tasksapp

import android.app.Application
import android.content.Context
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.tasksapp.di.AppComponent
import com.easyprog.tasksapp.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}