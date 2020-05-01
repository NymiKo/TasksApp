package com.easyprog.tasksapp.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.easyprog.domain.models.Tasks

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.activities.MainActivity
import com.easyprog.tasksapp.adapters.TaskAdapter
import com.easyprog.tasksapp.models.TaskClickHandler
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.fragment_task_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class TaskListFragment : Fragment() {

    private val mAdapter = TaskAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = NavHostFragment.findNavController(this)
        mAdapter.attachClickHandler(object : TaskClickHandler {
            override fun onItemClick(item: Tasks, id: Int) {
                navController.navigate(R.id.detailsTaskFragment)
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_task_list, container, false)
        val navController = NavHostFragment.findNavController(this)
        setHasOptionsMenu(true)

        //val extras = FragmentNavigatorExtras((view to "shared_element_container") as Pair<View, String>)

        fragmentLayout.btnCreateTask.setOnClickListener {
            navController.navigate(R.id.action_taskListFragment_to_createTaskFragment)
        }

        // Inflate the layout for this fragment
        return fragmentLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.task_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.exitApp -> {
                val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)!!
                sharedPreferences.edit().putString("token", "null").apply()
                startActivity(Intent(this.activity, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerTaskList.layoutManager = layoutManager
        recyclerTaskList.adapter = mAdapter

        fillTask()
    }

    fun fillTask() {
        val mockData = ArrayList<Tasks>()

        mockData.add(Tasks(id = 0, nameTask = "Задача 1", groupTask = true))
        mockData.add(Tasks(id = 1, nameTask = "Задача 2", groupTask = false))
        mockData.add(Tasks(id = 2, nameTask = "Задача 3", groupTask = false))
        mockData.add(Tasks(id = 3, nameTask = "Задача 4", groupTask = true))

        mAdapter.setData(newTask = mockData)
    }
}
