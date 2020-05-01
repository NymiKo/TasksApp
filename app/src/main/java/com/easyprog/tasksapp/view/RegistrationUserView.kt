package com.easyprog.tasksapp.view

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView

interface RegistrationUserView: MvpView {
    fun presentLoading()
    fun registerUserSuccessful(messageSuccess: Int)
    fun registerUserFailed(messageError: Int)
    fun repeatLogin(messageRepeatLogin: Int)
    fun repeatEmail(messageRepeatEmail: Int)
    fun invalidateEmail(messageInvalidateEmail: Int)
    fun invalidatePassword(messageInvalidatePassword: Int)
    fun invalidateOtherData(messageInvalidateOtherData: Int)
    fun noInternet(messageNoInternet: Int)
}