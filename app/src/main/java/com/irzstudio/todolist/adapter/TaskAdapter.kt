package com.irzstudio.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irzstudio.todolist.R
import com.irzstudio.todolist.db.TaskEntity
import kotlinx.android.synthetic.main.item_task.view.*
import java.text.SimpleDateFormat

class TaskAdapter(val onMoreClickListener : (data: TaskEntity, v: View) -> Unit): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var list : MutableList<TaskEntity> = mutableListOf()

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (taskEntity: TaskEntity) {

            itemView.txt_title.text = taskEntity.title
            itemView.txt_description.text = taskEntity.description

            itemView.time.text = taskEntity.time

            val sdfDay = SimpleDateFormat("E")
            val sdfDate = SimpleDateFormat("dd")
            val sdfMonth = SimpleDateFormat("MMM")

            val sdfOrigin = SimpleDateFormat("dd-MM-yyyy")
            val date = sdfOrigin.parse(taskEntity.date)

            itemView.date.text = sdfDate.format(date)
            itemView.day.text = sdfDay.format(date)
            itemView.month.text = sdfMonth.format(date)

            itemView.popup_option.setOnClickListener {
                onMoreClickListener.invoke(taskEntity, it)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataAdapter(data: List<TaskEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}
