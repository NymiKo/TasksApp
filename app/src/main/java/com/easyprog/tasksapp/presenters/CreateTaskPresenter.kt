package com.easyprog.tasksapp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.easyprog.domain.repositories.AddedParticipantRepository
import com.easyprog.domain.repositories.CreateTaskRepository
import com.easyprog.domain.repositories.implementations.AddedParticipantRepositoryImpl
import com.easyprog.domain.repositories.implementations.CreateTaskRepositoryImpl
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.App
import com.easyprog.tasksapp.view.CreateTaskView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@InjectViewState
class CreateTaskPresenter: MvpPresenter<CreateTaskView>() {

    @Inject
    lateinit var addedParticipantRepository: AddedParticipantRepository

    @Inject
    lateinit var createTaskRepository: CreateTaskRepository

    fun addedParticipant(email: String) {
        App.appComponent.inject(presenter = this@CreateTaskPresenter)

        if (emptyEmail(email = email)) {
            viewState.emptyEmail(messageError = R.string.message_empty_email)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addedParticipant = addedParticipantRepository
                    .addParticipantAsync(email = email).await()

                checkAnswerServer(addedParticipant.token, addedParticipant.name, addedParticipant.surname,
                    addedParticipant.avatar, addedParticipant.answer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun checkAnswerServer(token: String, name: String, surname: String, avatar: String, answer: String) {
        CoroutineScope(Dispatchers.Main).launch {
            when(answer) {
                "true" -> viewState.addParticipant(token = token, name = name, surname = surname, avatar = avatar)
                "false" -> viewState.errorAddParticipant(messageError = R.string.error_message_add_participant)
            }
        }
    }

    private fun emptyEmail(email: String): Boolean {
        return email.isEmpty()
    }

    fun createTask(
        name: String, description: String, color: String, end_date: String,
        type: Int, creator: String, participants: MutableList<String>
    ) {
        App.appComponent.inject(presenter = this@CreateTaskPresenter)
        viewState.presentLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val createTaskRepositoryImpl = createTaskRepository.createTaskAsync(name = name,
                    description = description, color = color, end_date = end_date, type = getTypeTask(type), creator = creator,
                    participants = getParticipantsCount(participants)).await()

                when(createTaskRepositoryImpl.answer) {
                    "true" -> {
                        insertTaskLocalStorage(id_on_server = createTaskRepositoryImpl.id_on_server, name = name,
                            description = description, color = color, end_date = end_date, type = getTypeTask(type), on_server = true)
                        withContext(Dispatchers.Main) {
                            viewState.backTaskListScreen(messageSuccess = R.string.success_message_create_task)
                        }
                    }
                    "false" -> {
                        withContext(Dispatchers.Main) {
                            viewState.errorCreateTask(messageError = R.string.error_message_create_task)
                        }
                    }
                }
            } catch (e: Exception) {
                insertTaskLocalStorage(id_on_server = 0, name = name, description = description, color = color,
                    end_date = end_date, type = getTypeTask(type), on_server = false)
                withContext(Dispatchers.Main) {
                    viewState.backTaskListScreen(messageSuccess = R.string.success_message_create_task)
                }
            }
        }
    }

    private fun insertTaskLocalStorage(id_on_server: Int, name: String, description: String, color: String, end_date: String,
                                       type: String, on_server: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                createTaskRepository.insertTaskLocal(id_on_server = id_on_server, name = name,
                    description = description, color = color, end_date = end_date, type = type, on_server = on_server, creator = true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getTypeTask(type: Int): String {
        return if(type > 0) "group"
        else "personal"
    }

    private fun getParticipantsCount(participants: MutableList<String>): MutableList<String>? {
        return if(participants.size > 0) participants
        else null
    }
}