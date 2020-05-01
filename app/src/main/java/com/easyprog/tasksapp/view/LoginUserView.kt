package com.easyprog.tasksapp.view

import com.arellomobile.mvp.MvpView

interface LoginUserView: MvpView {
    fun presentLoading()
    fun loginUserSuccess(token: String, name: String, surname: String, email: String)
    fun loginUserFailed(messageError: Int)
    fun invalidateLoginData(messageInvalidateLoginData: Int)
    fun noInternet(messageNoInternet: Int)
}