package com.easyprog.tasksapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.easyprog.domain.models.Participants

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.adapters.ParticipantAdapter
import kotlinx.android.synthetic.main.fragment_participants.*

/**
 * A simple [Fragment] subclass.
 */
class ParticipantsFragment : Fragment() {

    private val mAdapter = ParticipantAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerParticipantsTask.layoutManager = layoutManager
        recyclerParticipantsTask.adapter = mAdapter
        fillComments()
    }

    fun fillComments() {
        val mockData = ArrayList<Participants>()

        mockData.add(Participants(img = "", userName = "Имя1", userSurname = "Фамилия1"))
        mockData.add(Participants(img = "", userName = "Имя2", userSurname = "Фамилия2"))
        mockData.add(Participants(img = "", userName = "Имя3", userSurname = "Фамилия3"))
        mockData.add(Participants(img = "", userName = "Имя4", userSurname = "Фамилия4"))
        mockData.add(Participants(img = "", userName = "Имя5", userSurname = "Фамилия5"))

        mAdapter.setParticipants(participants = mockData)
    }
}
