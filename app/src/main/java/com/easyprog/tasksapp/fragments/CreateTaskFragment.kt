package com.easyprog.tasksapp.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import com.easyprog.domain.models.AddedParticipants
import com.easyprog.domain.models.Tasks
import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.adapters.AddedParticipantAdapter
import kotlinx.android.synthetic.main.fragment_create_task.*
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CreateTaskFragment : Fragment() {

    private val TAG = CreateTaskFragment::class.java.simpleName

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
            llAddParticipant.visibility = View.VISIBLE
            btnAddFieldParticipant.visibility = View.GONE
        }
    }

    private fun setupAdapter() {
        recyclerAddedParticipants.layoutManager = LinearLayoutManager(activity?.applicationContext,
            LinearLayoutManager.VERTICAL, false)
        recyclerAddedParticipants.adapter = mAdapter
        fillComments()
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

    fun fillComments() {
        val mockData = ArrayList<AddedParticipants>()

        mockData.add(AddedParticipants(image = "no_avatar", name = "Имя1", surname = "Фамилия1"))
        mockData.add(AddedParticipants(image = "https://i.pinimg.com/originals/1e/27/bc/1e27bc249c2429a93a52b4931a63c3ec.png", name = "Имя2", surname = "Фамилия2"))
        mockData.add(AddedParticipants(image = "https://avatars.mds.yandex.net/get-zen_doc/1542444/pub_5cf638dde77f2e00b01c9f9d_5cf63a1e388e2100af05c7e3/scale_1200", name = "Имя3", surname = "Фамилия3"))
        mockData.add(AddedParticipants(image = "no_avatar", name = "Имя4", surname = "Фамилия4"))
        mockData.add(AddedParticipants(image = "https://art-assorty.ru/wp-content/uploads/2019/06/Кэрол-Денверс.jpg", name = "Имя5", surname = "Фамилия5"))

        mAdapter.setParticipant(newParticipant = mockData)
    }
}
