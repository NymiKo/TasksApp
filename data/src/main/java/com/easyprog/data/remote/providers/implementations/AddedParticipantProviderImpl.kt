package com.easyprog.data.remote.providers.implementations

import com.easyprog.data.remote.models.AddedParticipantApi
import com.easyprog.data.remote.providers.AddedParticipantProvider
import com.easyprog.data.remote.services.RemoteAddedParticipantService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class AddedParticipantProviderImpl(val remoteAddedParticipantService: RemoteAddedParticipantService): AddedParticipantProvider {

    @UnstableDefault
    override fun addedParticipant(email: String): Deferred<AddedParticipantApi> {
        return remoteAddedParticipantService.addedParticipant(email = email)
    }
}