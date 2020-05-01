package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.implementations.LoginUserRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.di.App
import com.easyprog.tasksapp.view.LoginUserView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

@InjectViewState
class LoginUserPresenter: MvpPresenter<LoginUserView>() {

    fun loginUser(login: String, password: String) {

        if(!validateLoginData(login = login, password = password)) {
            viewState.invalidateLoginData(messageInvalidateLoginData = R.string.message_invalidate_login_data)
            return
        }

        viewState.presentLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val loginUserAnswer = LoginUserRepositoryImpl(roomDatabase = App.roomDatabase)
                    .LoginUserAsync(login = login, password = password).await()

                checkAnswerServer(loginUserAnswer.answer, token = loginUserAnswer.token, login = login, password = password,
                    avatar = loginUserAnswer.avatar, name = loginUserAnswer.name, surname = loginUserAnswer.surname, email = loginUserAnswer.email)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkAnswerServer(answer: String, token: String?, login: String?, password: String?, avatar: String?,
                                  name: String?, surname: String?, email: String?){
        CoroutineScope(Dispatchers.Main).launch {
            when (answer) {
                "false" -> viewState.loginUserFailed(messageError = R.string.error_message_login_user)
                "true" -> {
                    insertUserData(login = login!!, password = password!!, avatar = avatar!!, name = name!!, surname = surname!!, email = email!!)
                    viewState.loginUserSuccess(token = token!!, name = name, surname = surname, email = email)
                }
                else -> viewState.noInternet(messageNoInternet = R.string.message_no_internet)
            }
        }
    }

    private fun validateLoginData(login: String, password: String): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }

    private fun insertUserData(login: String, password: String, avatar: String, name: String, surname: String, email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                LoginUserRepositoryImpl(roomDatabase = App.roomDatabase).InsertUserDataAsync(login = login, password = password,
                    avatar = avatar, name = name, surname = surname, email = email)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}