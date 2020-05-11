package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.EditPersonalDataApi
import com.easyprog.data.remote.providers.EditPersonalDataProvider
import com.easyprog.data.remote.services.RemoteEditPersonalDataService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class EditPersonalDataProviderImpl(val remoteEditPersonalDataService: RemoteEditPersonalDataService): EditPersonalDataProvider {

    @UnstableDefault
    override fun editPersonalData(token: String, name: String, surname: String, email: String, login: String): Deferred<EditPersonalDataApi> {
        return remoteEditPersonalDataService.editPersonalData(token = token, name = name,
            surname = surname, email = email, login = login)
    }
}