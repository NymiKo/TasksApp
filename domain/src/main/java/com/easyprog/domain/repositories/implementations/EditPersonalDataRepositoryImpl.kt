package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.EditPersonalDataApi
import com.easyprog.data.remote.providers.EditPersonalDataProvider
import com.easyprog.data.remote.providers.implementations.EditPersonalDataProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.UserProfileEntity
import com.easyprog.domain.repositories.EditPersonalDataRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditPersonalDataRepositoryImpl(val editPersonalDataProvider: EditPersonalDataProvider,
                                     val roomDatabase: RoomDatabaseApp): EditPersonalDataRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun editPersonalDataAsync(token: String, name: String, surname: String, email: String, login: String): Deferred<EditPersonalDataApi> {

        return try {
            val editPersonalDataProvider = editPersonalDataProvider
                .editPersonalData(token = token, name = name, surname = surname, email = email, login = login).await()
            GlobalScope.async {
                editPersonalDataProvider
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun editPersonalDataLocalStorage(name: String, surname: String, email: String, login: String) {
        try {
            GlobalScope.async {
                roomDatabase?.userProfileDao()?.editUserPersonalData(name = name, surname = surname, email = email, login = login)
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }

    override suspend fun getPersonalData(): UserProfileEntity {

        return try {
            roomDatabase?.userProfileDao()!!.getUserProfile(id = 1)
        } catch (e: Exception) {
            error(e)
        }
    }


}