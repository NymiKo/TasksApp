package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.EditPersonalDataApi
import kotlinx.coroutines.Deferred

interface EditPersonalDataProvider {

    fun editPersonalData(token: String, name: String, surname: String, email: String, login: String): Deferred<EditPersonalDataApi>
}