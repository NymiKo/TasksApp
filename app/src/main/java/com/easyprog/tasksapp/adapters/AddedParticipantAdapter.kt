package com.easyprog.tasksapp.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.easyprog.domain.models.AddedParticipants
import com.easyprog.tasksapp.R
import com.squareup.picasso.Picasso

class AddedParticipantAdapter: RecyclerView.Adapter<AddedParticipantAdapter.ViewHolder>() {

    val mAddedParticipantsList: MutableList<AddedParticipants> = ArrayList()

    fun setParticipant(token: String, name: String, surname: String, avatar: String) {
        mAddedParticipantsList.add(AddedParticipants(token = token, name = name, surname = surname, image = avatar))

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_added_participant, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return mAddedParticipantsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        return viewHolder.bind(model = mAddedParticipantsList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val avatar: ImageView = itemView.findViewById(R.id.imageAvatarAddedParticipant)
        private val name = itemView.findViewById<TextView>(R.id.textNameAddedParticipant)

        @SuppressLint("SetTextI18n")
        fun bind(model: AddedParticipants) {
            if (model.image == "no_avatar") Picasso.get().load(R.mipmap.no_avatar).into(avatar)
            else Picasso.get().load(model.image).fit().centerCrop().into(avatar)

            name.text = "${model.name} ${model.surname}"
        }
    }
}