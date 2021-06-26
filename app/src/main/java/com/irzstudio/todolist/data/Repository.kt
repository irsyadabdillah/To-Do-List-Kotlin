package com.irzstudio.todolist.data

import com.irzstudio.todolist.db.TaskDb
import com.irzstudio.todolist.db.TaskEntity

class Repository {
    private val database by lazy {
        TaskDb.getInstance()
    }

    fun saveTask(title:String, description: String, date: String, time: String){
        val task = TaskEntity(title = title, description = description, date = date, time = time )
        database?.taskDao()?.insert(task)
    }

    fun updateTask(title:String, description: String, date: String, time: String, id:Int) {
        val task = TaskEntity(title = title, description = description, date = date, time = time, id = id)
        database?.taskDao()?.update(task)
    }

    fun remove(taskEntity: TaskEntity) {
        database?.taskDao()?.delete(taskEntity)
    }

    fun getAllDb(): List<TaskEntity> {
        val dataFromBd = database?.taskDao()?.getAll()
        return dataFromBd!!
    }


}