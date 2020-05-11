package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.SendTasksListOnServerRepository
import com.easyprog.domain.repositories.TasksNotServerRepository
import com.easyprog.domain.repositories.implementations.SendTasksListOnServerRepositoryImpl
import com.easyprog.domain.repositories.implementations.TasksNotServerRepositoryImpl
import com.easyprog.tasksapp.App
import com.easyprog.tasksapp.view.SplashScreenView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenPresenter: MvpPresenter<SplashScreenView>() {

    @Inject
    lateinit var sendTasksListOnServerRepository: SendTasksListOnServerRepository

    @Inject
    lateinit var tasksListOnServerRepository: TasksNotServerRepository

    fun sendTasksOnServer(token: String) {
        App.appComponent.inject(presenter = this@SplashScreenPresenter)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tasksList = tasksListOnServerRepository.getTasks()

                sendTasksListOnServerRepository.sendTasksAsync(tasksListEntity = tasksList, token = token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}