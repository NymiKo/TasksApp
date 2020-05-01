package com.easyprog.tasksapp.presenters

import android.graphics.Bitmap
import android.util.Base64
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.implementations.ChangePasswordRepositoryImpl
import com.easyprog.domain.repositories.implementations.ProfileRepositoryImpl
import com.easyprog.domain.repositories.implementations.UpdateAvatarRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.di.App
import com.easyprog.tasksapp.view.ProfileView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.Exception

@InjectViewState
class ProfilePresenter: MvpPresenter<ProfileView>() {

    fun getUserProfile() {
        val showUserProfileRepository = ProfileRepositoryImpl(roomDatabase = App.roomDatabase)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profile = showUserProfileRepository.userProfileAsync()
                withContext(Dispatchers.Main) {
                    viewState.presentProfile(profile.avatar, profile.name, profile.surname, profile.email)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateAvatar(token: String, avatar: Bitmap?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val updateAvatarAnswer = UpdateAvatarRepositoryImpl(roomDatabase = App.roomDatabase)
                    .updateAvatarAsync(token = token, avatar = imageToString(avatar = avatar)).await()

                checkAnswerServer(answer = updateAvatarAnswer.answer)
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    viewState.updateAvatarError(messageError = R.string.error_message_update_avatar)
                }
            }
        }
    }

    private fun checkAnswerServer(answer: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when (answer) {
                "false" -> viewState.updateAvatarError(messageError = R.string.error_message_update_avatar)
                else -> updateAvatarInLocalStorage(avatar = answer)
            }
        }
    }

    private fun updateAvatarInLocalStorage(avatar: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                UpdateAvatarRepositoryImpl(roomDatabase = App.roomDatabase).updateAvatarInLocalStorage(avatar = avatar)
                withContext(Dispatchers.Main) {
                    viewState.updateAvatarInView(avatar = avatar)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun imageToString(avatar: Bitmap?): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        avatar?.compress(Bitmap.CompressFormat.JPEG,50, byteArrayOutputStream)
        val imageByte = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imageByte, Base64.DEFAULT)
    }
}