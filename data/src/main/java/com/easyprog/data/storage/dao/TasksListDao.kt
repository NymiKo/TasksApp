package com.easyprog.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.easyprog.data.storage.contract.RoomContract
import com.easyprog.data.storage.model.TasksListEntity

@Dao
interface TasksListDao {

    @Query("SELECT * FROM ${RoomContract.tableTasks} WHERE on_server = :on_server")
    fun getTasksNotServer(on_server: Boolean): List<TasksListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(tasksListEntity: TasksListEntity)
}