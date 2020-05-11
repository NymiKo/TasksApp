package com.easyprog.domain.repositories.implementations

import com.easyprog.data.remote.models.AddedParticipantApi
import com.easyprog.data.remote.providers.AddedParticipantProvider
import com.easyprog.data.remote.providers.implementations.AddedParticipantProviderImpl
import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.domain.repositories.AddedParticipantRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddedParticipantRepositoryImpl(val addedParticipantProvider: AddedParticipantProvider): AddedParticipantRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun addParticipantAsync(email: String): Deferred<AddedParticipantApi> {

        return try {
            GlobalScope.async {
                addedParticipantProvider.addedParticipant(email = email).await()
            }
        } catch (e: Exception) {
            GlobalScope.async { error(e) }
        }
    }
}