package com.easyprog.domain.repositories

import com.easyprog.data.remote.models.EditPersonalDataApi
import com.easyprog.data.storage.model.UserProfileEntity
import kotlinx.coroutines.Deferred

interface EditPersonalDataRepository {
    suspend fun editPersonalDataAsync(token: String, name: String, surname: String, email: String, login: String): Deferred<EditPersonalDataApi>
    suspend fun editPersonalDataLocalStorage(name: String, surname: String, email: String, login: String)
    suspend fun getPersonalData(): UserProfileEntity
}