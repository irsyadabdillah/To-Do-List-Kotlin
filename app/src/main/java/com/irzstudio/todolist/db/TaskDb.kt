package com.irzstudio.todolist.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.irzstudio.todolist.AppController

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDb? = null

        fun getInstance(): TaskDb? {
            if (INSTANCE == null) {
                synchronized(TaskDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        AppController.getInstance().applicationContext,
                        TaskDb::class.java, "favoritedata.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}