package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.EditPersonalDataRepository
import com.easyprog.domain.repositories.implementations.EditPersonalDataRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.App
import com.easyprog.tasksapp.view.EditPersonalDataView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@InjectViewState
class EditPersonalDataPresenter: MvpPresenter<EditPersonalDataView>() {

    @Inject
    lateinit var editPersonalDataRepository: EditPersonalDataRepository

    fun editPersonalData(token: String, name: String, surname: String, email: String, login: String) {
        App.appComponent.inject(presenter = this@EditPersonalDataPresenter)

        if(!validateEmail(email = email))
            viewState.invalidatePersonalDataEmail(messageInvalidatePersonalDataEmail = R.string.message_invalidate_email)

        if (!validateOtherData(name = name, surname = surname, login = login))
            viewState.emptyPersonalData(messageEmptyPersonalData = R.string.message_invalidate_other_data)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val editPersonalDataAnswer = editPersonalDataRepository
                    .editPersonalDataAsync(token = token, name = name, surname = surname, email = email, login = login).await()

                checkAnswerServer(answer = editPersonalDataAnswer.answer, name = name,
                    surname = surname, email = email, login = login)
            } catch (e:Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    viewState.noInternet(messageNoInternet = R.string.message_no_internet)
                }
            }
        }
    }

    fun getPersonalData() {
        App.appComponent.inject(presenter = this@EditPersonalDataPresenter)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profile = editPersonalDataRepository.getPersonalData()
                withContext(Dispatchers.Main) {
                    viewState.presentProfile(profile.name, profile.surname, profile.email, profile.login)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkAnswerServer(answer: String, name: String, surname: String, email: String, login: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when(answer) {
                "true" -> {
                    editPersonalDataLocaleStorage(name = name, surname = surname, email = email, login = login)
                    viewState.editPersonalDataSuccess(messageSuccess = R.string.success_message_edit_personal_data)
                }
                "repeatLogin" -> viewState.repeatPersonalDataLogin(messageRepeatPersonalDataLogin = R.string.repeat_login_message)
                "repeatEmail" -> viewState.repeatPersonalDataEmail(messageRepeatPersonalDataEmail = R.string.repeat_email_message)
                "false" -> viewState.editPersonalDataError(messageError = R.string.error_message_edit_personal_data)
            }
        }
    }

    private fun editPersonalDataLocaleStorage(name: String, surname: String, email: String, login: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                editPersonalDataRepository.editPersonalDataLocalStorage(name = name,
                    surname = surname, email = email, login = login)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@" ) && email.contains(".")
    }

    private fun validateOtherData(login: String, name: String, surname: String): Boolean {
        return login.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()
    }
}