package com.easyprog.domain.repositories

import com.easyprog.data.storage.model.UserProfileEntity

interface ProfileRepository {

    fun userProfileAsync(): UserProfileEntity
}