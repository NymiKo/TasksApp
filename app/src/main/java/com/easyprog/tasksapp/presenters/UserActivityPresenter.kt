package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.ProfileRepository
import com.easyprog.domain.repositories.implementations.ProfileRepositoryImpl
import com.easyprog.tasksapp.App
import com.easyprog.tasksapp.view.UserActivityView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class UserActivityPresenter: MvpPresenter<UserActivityView>() {

    @Inject
    lateinit var profileRepository: ProfileRepository

    fun getUserAvatar(){
        App.appComponent.inject(presenter = this@UserActivityPresenter)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profile = profileRepository.userProfileAsync()
                withContext(Dispatchers.Main) {
                    viewState.fillNavHeader(profile.avatar)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}