package com.easyprog.tasksapp.view

import com.arellomobile.mvp.MvpView

interface EditPersonalDataView: MvpView {
    fun presentProfile(name: String, surname: String, email: String, login: String)
    fun presentLoading()
    fun editPersonalDataSuccess(messageSuccess: Int)
    fun editPersonalDataError(messageError: Int)
    fun emptyPersonalData(messageEmptyPersonalData: Int)
    fun invalidatePersonalDataEmail(messageInvalidatePersonalDataEmail: Int)
    fun repeatPersonalDataEmail(messageRepeatPersonalDataEmail: Int)
    fun repeatPersonalDataLogin(messageRepeatPersonalDataLogin: Int)
    fun noInternet(messageNoInternet: Int)
}