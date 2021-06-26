package com.irzstudio.todolist.ui.createtask

import androidx.lifecycle.ViewModel
import com.irzstudio.todolist.data.Repository

class CreateTaskViewModel : ViewModel() {
    private val repository by lazy {
        Repository()
    }

    fun saveTask(title:String, description: String, date: String, time: String){
        repository.saveTask(title = title, description = description, date = date, time = time)
    }

    fun updateTask(title:String, description: String, date: String, time: String, id:Int) {
        repository.updateTask(title = title, description = description, date = date, time = time, id = id)

    }


}