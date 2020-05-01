package com.easyprog.data.storage.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.easyprog.data.storage.contract.RoomContract

@Entity(tableName = RoomContract.tableUserProfile)
data class UserProfileEntity(@PrimaryKey val id: Int, val login: String, val avatar: String, val name: String, val surname: String, val email: String)