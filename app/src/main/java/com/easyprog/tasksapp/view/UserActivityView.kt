package com.easyprog.tasksapp.view

import com.arellomobile.mvp.MvpView

interface UserActivityView: MvpView {
    fun fillNavHeader(avatar: String)
}