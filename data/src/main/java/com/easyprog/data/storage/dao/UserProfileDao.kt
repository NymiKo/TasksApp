package com.easyprog.data.storage.dao

import android.arch.persistence.room.*
import com.easyprog.data.storage.contract.RoomContract
import com.easyprog.data.storage.model.TasksListEntity
import com.easyprog.data.storage.model.UserProfileEntity

@Dao
interface UserProfileDao {

    @Query("SELECT * FROM ${RoomContract.tableUserProfile} WHERE id = :id")
    fun getUserProfile(id: Int): UserProfileEntity

//    @Query("SELECT password FROM ${RoomContract.tableUserProfile} WHERE id = :id")
//    fun getUserPassword(id: Int): GetPasswordHelper

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(userProfileEntity: UserProfileEntity)

    @Query("UPDATE ${RoomContract.tableUserProfile} SET name = :name, surname = :surname, email = :email, login = :login WHERE id = 1")
    fun editUserPersonalData(name: String, surname: String, email: String, login: String)

    @Query("UPDATE ${RoomContract.tableUserProfile} SET avatar = :avatar WHERE id = 1")
    fun updateAvatar(avatar: String)
}