package com.easyprog.tasksapp.view

import com.arellomobile.mvp.MvpView

interface ChangePasswordView: MvpView {
    fun changePasswordSuccess(messageSuccess: Int)
    fun changePasswordError(messageError: Int)
    fun errorWithNewPassword(messageError: Int)
    fun errorWithOldPassword(messageError: Int)
}