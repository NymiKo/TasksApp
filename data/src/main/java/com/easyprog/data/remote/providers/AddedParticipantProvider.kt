package com.easyprog.data.remote.providers

import com.easyprog.data.remote.models.AddedParticipantApi
import kotlinx.coroutines.Deferred

interface AddedParticipantProvider {
    fun addedParticipant(email: String): Deferred<AddedParticipantApi>
}