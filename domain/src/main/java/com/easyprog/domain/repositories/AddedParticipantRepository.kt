package com.easyprog.domain.repositories

import android.provider.ContactsContract
import com.easyprog.data.remote.models.AddedParticipantApi
import kotlinx.coroutines.Deferred

interface AddedParticipantRepository {
    fun addParticipantAsync(email: String): Deferred<AddedParticipantApi>
}