package com.irzstudio.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.todolist.data.Repository
import com.irzstudio.todolist.db.TaskEntity

class MainViewModel: ViewModel() {

    private val repository by lazy {
        Repository()
    }

    private val _taskList = MutableLiveData<ArrayList<TaskEntity>>()
    val taskList: LiveData<ArrayList<TaskEntity>> = _taskList

    private var dataTask: ArrayList<TaskEntity> = ArrayList()

    fun loadTaskData() {
        dataTask.clear()
        dataTask.addAll(repository.getAllDb())
        _taskList.postValue(dataTask)
    }

    fun removeData(taskEntity: TaskEntity) {
        repository.remove(taskEntity)
    }


}