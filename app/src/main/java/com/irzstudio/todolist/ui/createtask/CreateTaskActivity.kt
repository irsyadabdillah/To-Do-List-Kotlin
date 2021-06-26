package com.irzstudio.todolist.ui.createtask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.irzstudio.todolist.R
import com.irzstudio.todolist.db.TaskEntity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_create_task.*
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    private val viewModel: CreateTaskViewModel by lazy {
        ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)
    }

    private val taskExtra: TaskEntity? by lazy {
        intent.getParcelableExtra("task")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        setTaskExtra()
        createTask()
        cancelTask()
        initView()
    }

    private fun initView() {
        task_date.setOnClickListener {
            val calender = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                this,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH))

            dpd.show(supportFragmentManager, "")
        }
        task_time.setOnClickListener {
            val time = Calendar.getInstance()
            val tpd = TimePickerDialog.newInstance(this,
            time.get(Calendar.HOUR),
            time.get(Calendar.MINUTE), true)

            tpd.show(supportFragmentManager, "")
        }
    }

    private fun cancelTask() {
        tv_cancel.setOnClickListener {
            finish()
        }
    }

    private fun createTask() {
        btn_create_task.setOnClickListener {
            if (et_title.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Please fill the title", Toast.LENGTH_SHORT
                ).show()
            } else if (et_description.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Please fill the description", Toast.LENGTH_SHORT
                ).show()
            } else if (task_time.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Please choose the time", Toast.LENGTH_SHORT
                ).show()
            } else if (task_date.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Please choose the date", Toast.LENGTH_SHORT
                ).show()
            } else {
                saveOrUpdateTask()
                finish()
            }
        }
    }

    private fun setTaskExtra() {
        if (taskExtra != null) {
            et_title.setText(taskExtra?.title)
            et_description.setText(taskExtra?.description)
            task_date.setText(taskExtra?.date)
            task_time.setText(taskExtra?.time)
        }
    }

    private fun saveOrUpdateTask() {
        val title = et_title.text.toString()
        val description = et_description.text.toString()
        val date = task_date.text.toString()
        val time = task_time.text.toString()
        if (taskExtra == null) {
            viewModel.saveTask(title, description, date, time)
        } else {
            viewModel.updateTask(title, description, date, time, taskExtra?.id ?: 0)
        }
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR, hourOfDay)
        cal.set(Calendar.MINUTE, minute)

        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(cal.time)
        task_time.setText(time)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        cal.set(Calendar.MONTH, monthOfYear)
        cal.set(Calendar.YEAR, year)

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date = sdf.format(cal.time)
        task_date.setText(date)
    }


}