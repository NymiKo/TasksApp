package com.easyprog.tasksapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import com.easyprog.domain.models.Comments

import com.easyprog.tasksapp.R
import com.easyprog.tasksapp.adapters.CommentAdapter
import kotlinx.android.synthetic.main.bottom_sheet.*

class DetailsTaskFragment : Fragment() {

    private val mAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_details_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.details_task_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = true
        recyclerCommentsList.layoutManager = layoutManager
        recyclerCommentsList.adapter = mAdapter

        fillComments()
        textNoComments.visibility = View.GONE
        recyclerCommentsList.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = NavHostFragment.findNavController(this)
        when(item?.itemId) {
            R.id.deleteTaskItemMenu -> {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.title_dialog_delete_task)
                    .setMessage(R.string.message_dialog_delete_task)
                    .setPositiveButton(R.string.positiveBtn_dialog_delete_task, null)
                    .setNegativeButton(R.string.negativeBtn_dialog_delete_task, null)
                    .create()
                    .show()
            }
            R.id.participantsFragmentItem -> navController.navigate(R.id.participantsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    fun fillComments() {
        val mockData = ArrayList<Comments>()

        mockData.add(Comments(id = 0, sender = "Какой-то отправитель", comment = "Комментарий номер 1"))
        mockData.add(Comments(id = 1, sender = "Какой-то отправитель 2", comment = "Комментарий номер 2"))
        mockData.add(Comments(id = 2, sender = "Кто-то", comment = "Бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-="))


        mAdapter.setComments(newComment = mockData)
    }
}
