package com.easyprog.domain.repositories.implementations

import com.easyprog.data.storage.RoomDatabaseApp
import com.easyprog.data.storage.model.UserProfileEntity
import com.easyprog.domain.repositories.ProfileRepository
import java.lang.Exception

class ProfileRepositoryImpl(val roomDatabase: RoomDatabaseApp): ProfileRepository {

    @Suppress("UNREACHABLE_CODE")
    override fun userProfileAsync(): UserProfileEntity {
        return try {
            roomDatabase.userProfileDao().getUserProfile(id = 1)
        } catch (e: Exception) {
            error(e)
        }
    }


}