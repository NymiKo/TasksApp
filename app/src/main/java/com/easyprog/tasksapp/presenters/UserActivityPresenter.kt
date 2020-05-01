package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.implementations.ProfileRepositoryImpl
import com.easyprog.tasksapp.di.App
import com.easyprog.tasksapp.view.UserActivityView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@InjectViewState
class UserActivityPresenter: MvpPresenter<UserActivityView>() {

    fun getUserAvatar(){
        val showUserProfileRepository = ProfileRepositoryImpl(roomDatabase = App.roomDatabase)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profile = showUserProfileRepository.userProfileAsync()
                withContext(Dispatchers.Main) {
                    viewState.fillNavHeader(profile.avatar)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}