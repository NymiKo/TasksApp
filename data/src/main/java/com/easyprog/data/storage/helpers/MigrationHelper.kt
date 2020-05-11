package com.easyprog.data.storage.helpers

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import com.easyprog.data.storage.contract.RoomContract


class MigrationHelper {

    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE ${RoomContract.tableTasks} (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_on_server INTEGER NULL, name TEXT NOT NULL, " +
                    "description TEXT NOT NULL, color TEXT NOT NULL, end_date TEXT, type TEXT NOT NULL, " +
                    "on_server INTEGER NOT NULL DEFAULT 0, creator INTEGER NOT NULL DEFAULT(0))")
//            "id INTEGER NOT NULL, id_on_server INTEGER, name STRING NOT NULL, description STRING NOT NULL, color STRING NOT NULL, end_date STRING, type STRING NOT NULL, on_server BOOLEAN NOT NULL, creator BOOLEAN NOT NULL, PRIMARY KEY(id))" +
//                    "CREATE TABLE `a0389729_TaskApp`.`TasksList` ( `id` INT NOT NULL , " +
//                    "`id_on_server` INT NOT NULL , `name` VARCHAR(10) NOT NULL , `description` VARCHAR(10) NOT NULL , " +
//                    "`color` VARCHAR(20) NOT NULL , `end_date` VARCHAR(50) NOT NULL , `type` VARCHAR(1000) NOT NULL , " +
//                    "`on_server` BOOLEAN NOT NULL , `creator` BOOLEAN NOT NULL )"
        }
    }
}