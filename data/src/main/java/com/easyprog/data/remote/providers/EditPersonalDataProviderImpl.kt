package com.easyprog.data.remote.providers

import com.easyprog.data.remote.helpers.RetrofitFactory
import com.easyprog.data.remote.models.EditPersonalDataApi
import com.easyprog.data.remote.services.EditPersonalDataService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault
import retrofit2.create

class EditPersonalDataProviderImpl {

    @UnstableDefault
    fun editPersonalData(token: String, name: String, surname: String, email: String, login: String): Deferred<EditPersonalDataApi> {
        return RetrofitFactory.getRetrofitClient().create(EditPersonalDataService::class.java)
            .editPersonalData(token = token, name = name, surname = surname, email = email, login = login)
    }
}