package com.easyprog.tasksapp.view

import com.arellomobile.mvp.MvpView

interface CreateTaskView: MvpView {
    fun addParticipant(token: String, name: String, surname: String, avatar: String)
    fun errorAddParticipant(messageError: Int)
    fun emptyEmail(messageError: Int)
    fun presentLoading()
    fun backTaskListScreen(messageSuccess: Int)
    fun errorCreateTask(messageError: Int)
}