package com.easyprog.tasksapp.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.easyprog.domain.models.AddedParticipants
import com.easyprog.domain.models.Tasks
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.adapters.AddedParticipantAdapter
import com.easyprog.tasksapp.presenters.CreateTaskPresenter
import com.easyprog.tasksapp.view.CreateTaskView
import kotlinx.android.synthetic.main.fragment_create_task.*
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CreateTaskFragment : MvpAppCompatFragment(), CreateTaskView {

    private val TAG = CreateTaskFragment::class.java.simpleName

    @InjectPresenter
    lateinit var createTaskPresenter: CreateTaskPresenter

    var mAdapter = AddedParticipantAdapter()
    var defaultColor = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        defaultColor = resources.getColor(R.color.colorPrimary)

        btnColorPicker.setOnClickListener { openColorPicker() }

        btnDatePicker.setOnClickListener { openDatePicker() }

        btnAddFieldParticipant.setOnClickListener {
            if(llAddParticipant.visibility != View.VISIBLE) {
                btnAddFieldParticipant.background = resources.getDrawable(R.mipmap.close_field_participant)
                llAddParticipant.visibility = View.VISIBLE
            } else {
                btnAddFieldParticipant.background = resources.getDrawable(R.mipmap.add_participant)
                llAddParticipant.visibility = View.GONE
            }
        }

        btnAddParticipant.setOnClickListener {
            createTaskPresenter.addedParticipant(email = editTextAddParticipant.text.toString())
        }

        btnCreateTask.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val token = sharedPreferences?.getString("token", null)

            createTaskPresenter.createTask(
                name = editNameTask.text.toString(), description = editDescriptionTask.text.toString(),
            color = defaultColor.toString(), end_date = textEndDate.text.toString(), creator = token!!, type = mAdapter.itemCount,
            participants = mAdapter.mAddedParticipantsList.map { it.token }.toMutableList()
            )
        }
    }

    private fun setupAdapter() {
        recyclerAddedParticipants.layoutManager = LinearLayoutManager(activity?.applicationContext,
            LinearLayoutManager.VERTICAL, false)
        recyclerAddedParticipants.adapter = mAdapter
    }

    private fun openColorPicker() {
        val colorPicker =
            AmbilWarnaDialog(activity, defaultColor, object : OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog) {

                }
                override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                    defaultColor = color
                    imageSelectedColor.setBackgroundColor(defaultColor)
                }
            })
        colorPicker.show()
    }

    @SuppressLint("NewApi")
    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            textEndDate.text = DateUtils.formatDateTime(activity, calendar.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
    }

    override fun addParticipant(token: String, name: String, surname: String, avatar: String) {
        mAdapter.setParticipant(name = name, surname = surname, avatar = avatar, token = token)
        editTextAddParticipant.text?.clear()
        editTextLayoutAddParticipant.error = null
    }

    override fun errorAddParticipant(messageError: Int) {
        Toast.makeText(activity, messageError, Toast.LENGTH_SHORT).show()
        editTextLayoutAddParticipant.error = null
    }

    override fun emptyEmail(messageError: Int) {
        editTextLayoutAddParticipant.error = getString(messageError)
    }

    override fun presentLoading() {
        blackoutBackgroundCreateTask.visibility = View.VISIBLE
    }

    override fun backTaskListScreen(messageSuccess: Int) {
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.taskListFragment)
        Toast.makeText(activity, messageSuccess, Toast.LENGTH_SHORT).show()
    }

    override fun errorCreateTask(messageError: Int) {
        Toast.makeText(activity, messageError, Toast.LENGTH_SHORT).show()
    }
}
