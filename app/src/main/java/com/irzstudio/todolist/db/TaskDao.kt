package com.irzstudio.todolist.db

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insert(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)

    @Update
    fun update(taskEntity: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * From task Where id = :id")
    fun getById(id:Int): List<TaskEntity>
}