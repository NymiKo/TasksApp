package com.easyprog.tasksapp.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.easyprog.domain.models.Participants
import com.easyprog.tasksapp.R
import com.squareup.picasso.Picasso
import java.text.FieldPosition
import java.util.ArrayList

class ParticipantAdapter: RecyclerView.Adapter<ParticipantAdapter.ViewHolder>() {

    private val mParticipantsList: MutableList<Participants> = ArrayList()

    fun setParticipants(participants: List<Participants>) {
        mParticipantsList.clear()
        mParticipantsList.addAll(participants)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_participant, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return mParticipantsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mParticipantsList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textNameParticipant: TextView = itemView.findViewById(R.id.textNameParticipant)
        private val imageAvatarParticipant: ImageView = itemView.findViewById(R.id.imageAvatarParticipant)

        @SuppressLint("SetTextI18n")
        fun bind(model: Participants) {
            textNameParticipant.text = "${model.userName} ${model.userSurname}"
            Picasso.get().load(R.mipmap.avatar00).into(imageAvatarParticipant)
        }
    }
}