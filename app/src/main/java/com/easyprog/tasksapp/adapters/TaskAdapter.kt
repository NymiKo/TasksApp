package com.easyprog.tasksapp.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.easyprog.domain.models.Tasks
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.models.TaskClickHandler
import com.squareup.picasso.Picasso

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val mTaskList: MutableList<Tasks> = ArrayList()
    private var taskClickHandler: TaskClickHandler? = null

    fun setData(newTask: List<Tasks>) {
        mTaskList.clear()
        mTaskList.addAll(newTask)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_task, viewGroup, false),
            taskClickHandler = taskClickHandler)
    }

    override fun getItemCount(): Int {
        return mTaskList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mTaskList[position])
    }

    fun attachClickHandler(taskClickHandler: TaskClickHandler) {
        this.taskClickHandler = taskClickHandler
    }

    class ViewHolder(itemView: View, private val taskClickHandler: TaskClickHandler?): RecyclerView.ViewHolder(itemView) {

        //private val textNameTask: TextView = itemView.findViewById(R.id.textNameTask)
        private val imageGroupTask: ImageView = itemView.findViewById(R.id.imageGroupTask)
        private val cellTask = itemView.findViewById<FrameLayout>(R.id.llCellTask)

        fun bind(model: Tasks) {
            //textNameTask.text = model.nameTask
            if(model.groupTask) {
                Picasso.get().load(R.drawable.group_true).into(imageGroupTask)
            } else {
                Picasso.get().load(R.drawable.group_false).into(imageGroupTask)
            }

            cellTask.setOnClickListener {
                taskClickHandler?.onItemClick(item = model, id = model.id)
            }
        }
    }
}