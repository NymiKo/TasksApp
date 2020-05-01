package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.implementations.ChangePasswordRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.di.App
import com.easyprog.tasksapp.view.ChangePasswordView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@InjectViewState
class ChangePasswordPresenter: MvpPresenter<ChangePasswordView>() {

    fun changePassword(token: String, oldPassword: String, newPassword: String) {

        if(!validatePassword(oldPassword = oldPassword, newPassword = newPassword)) {
            viewState.changePasswordError(messageError = R.string.message_invalidate_other_data)
            return
        }

        if (!lengthPassword(newPassword = newPassword)) {
            viewState.errorWithNewPassword(messageError = R.string.message_invalidate_password)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val changePasswordAnswer = ChangePasswordRepositoryImpl(roomDatabase = App.roomDatabase)
                    .changePasswordAsync(token = token, oldPassword = oldPassword, newPassword = newPassword).await()

                checkAnswerServer(answer = changePasswordAnswer.answer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkAnswerServer(answer: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when(answer) {
                "false" -> viewState.changePasswordError(messageError = R.string.error_message_change_password)
                "wrongPassword" -> viewState.errorWithOldPassword(messageError = R.string.message_wrong_password)
                "repeatingOldPassword" -> viewState.errorWithNewPassword(messageError = R.string.error_message_repeat_old_password)
                "true" -> viewState.changePasswordSuccess(messageSuccess = R.string.success_message_change_password)
            }
        }
    }

    private fun validatePassword(oldPassword: String, newPassword: String): Boolean {
        return oldPassword.isNotEmpty() && newPassword.isNotEmpty()
    }

    private fun lengthPassword(newPassword: String): Boolean {
        return newPassword.length >= 8
    }
}