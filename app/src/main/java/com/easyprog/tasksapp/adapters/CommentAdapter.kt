package com.easyprog.tasksapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.easyprog.domain.models.Comments
import com.easyprog.tasksapp.R
import java.util.*

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val mCommentsList: MutableList<Comments> = ArrayList()

    fun setComments(newComment: List<Comments>) {
        mCommentsList.clear()
        mCommentsList.addAll(newComment)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_comment, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return mCommentsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mCommentsList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textSender: TextView = itemView.findViewById(R.id.textSender)
        private val textComment: TextView = itemView.findViewById(R.id.textComment)
        private val llComment = itemView.findViewById<LinearLayout>(R.id.llComment)

        fun bind(model: Comments) {
            if(model.sender == "Это я") textComment.text = "Пидор"
            else textComment.text = model.comment

            textSender.text = model.sender

        }
    }

}