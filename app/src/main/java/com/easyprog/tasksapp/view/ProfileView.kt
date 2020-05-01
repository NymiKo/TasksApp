package com.easyprog.tasksapp.view

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView

interface ProfileView: MvpView {
    fun presentProfile(avatar: String, name: String, surname: String, email: String)
    fun updateAvatarError(messageError: Int)
    fun updateAvatarInView(avatar: String)
}