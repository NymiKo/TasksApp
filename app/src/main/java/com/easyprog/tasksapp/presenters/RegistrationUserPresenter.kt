package com.easyprog.tasksapp.presenters

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.implementations.RegistrationUserRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.view.RegistrationUserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.Exception

@InjectViewState
class RegistrationUserPresenter: MvpPresenter<RegistrationUserView>() {

    fun registrationUser(
        token: String, login: String, password: String, image: Bitmap?, name: String,
        surname: String, email: String) {

        if(!validateOtherData(login = login, name = name, surname = surname)) {
            viewState.invalidateOtherData(R.string.message_invalidate_other_data)
            return
        }

        if(!validatePassword(password = password)) {
            viewState.invalidatePassword(R.string.message_invalidate_password)
            return
        }

        if(!validateEmail(email = email)) {
            viewState.invalidateEmail(R.string.message_invalidate_email)
            return
        }

        viewState.presentLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val registrationUserAnswer = RegistrationUserRepositoryImpl()
                    .RegistrationUserAsync(token = token, login = login, password = password, avatar = imageToString(image = image),
                        name = name, surname = surname, email = email).await()

                checkAnswerServer(registrationUserAnswer.answer)
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    viewState.noInternet(R.string.message_no_internet)
                }
            }
        }
    }

    private fun checkAnswerServer(answer: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when (answer) {
                "true" -> viewState.registerUserSuccessful(messageSuccess = R.string.success_message_register_user)
                "repeatEmail" -> viewState.repeatEmail(messageRepeatEmail = R.string.repeat_email_message)
                "repeatLogin" -> viewState.repeatLogin(messageRepeatLogin = R.string.repeat_login_message)
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 8
    }

    private fun validateOtherData(login: String, name: String, surname: String): Boolean {
        return login.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()
    }

    private fun imageToString(image: Bitmap?): String {
        return if (image == null) "no_avatar"
        else {
            val byteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG,50, byteArrayOutputStream)
            val imageByte = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(imageByte, Base64.DEFAULT)
        }
    }
}