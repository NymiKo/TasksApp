package com.easyprog.data.storage.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.easyprog.data.storage.contract.RoomContract

@Entity(tableName = RoomContract.tableTasks)
data class TasksListEntity(@PrimaryKey(autoGenerate = true) val id: Int = 0, val id_on_server: Int?, val name: String,
                           val description: String, val color: String, val end_date: String?,
                           val type: String, val on_server: Boolean, val creator: Boolean)