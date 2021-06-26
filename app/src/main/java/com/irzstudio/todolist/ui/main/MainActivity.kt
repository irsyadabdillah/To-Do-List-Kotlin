package com.irzstudio.todolist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.irzstudio.todolist.R
import com.irzstudio.todolist.adapter.TaskAdapter
import com.irzstudio.todolist.db.TaskEntity
import com.irzstudio.todolist.ui.createtask.CreateTaskActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_task.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    private val taskAdapter: TaskAdapter by lazy {
        TaskAdapter{data, view ->
            val popMenu = PopupMenu(this@MainActivity, view)
            popMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_update -> {
                        val intent = Intent(applicationContext, CreateTaskActivity::class.java)
                        intent.putExtra("task", data)
                        startActivity(intent)
                    }
                    R.id.menu_delete -> {
                        viewModel.removeData(data)
                        viewModel.loadTaskData()
                    }
                }
                true
            }
            popMenu.inflate(R.menu.menu)
            popMenu.show()
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createTask()
        setDataTask()
        observeTaskList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTaskData()
    }

    private fun setLayoutVisibility(dataTask : ArrayList<TaskEntity>) {
        if (dataTask.size == 0){
            layout_task.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE
        }else{
            layout_task.visibility = View.VISIBLE
            layout_empty.visibility = View.GONE
        }
    }

    private fun createTask() {
        btn_create.setOnClickListener {
            startActivity(Intent(applicationContext, CreateTaskActivity::class.java))
        }
    }

    private fun observeTaskList() {
        viewModel.taskList.observe(this, {
            taskAdapter.setDataAdapter(it)
            setLayoutVisibility(it)
        })
    }

    private fun setDataTask() {
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = taskAdapter
    }

}