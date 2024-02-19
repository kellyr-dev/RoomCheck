package com.example.userroom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.userroom.R
import com.example.userroom.data.model.User
import com.example.userroom.fragments.ListFragmentDirections
import com.squareup.picasso.Picasso


class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]

        holder.itemView.findViewById<TextView>(R.id.text_fname).text = currentUser.firstName
        holder.itemView.findViewById<TextView>(R.id.text_lname).text = currentUser.lastName
        holder.itemView.findViewById<TextView>(R.id.text_email).text = currentUser.email
        holder.itemView.findViewById<TextView>(R.id.text_age).text = currentUser.age.toString()
        //holder.itemView.show_user_image =
        Picasso.get().load(R.drawable.user_placeholder)
            .placeholder(R.drawable.user_placeholder)
            .resize(300, 300).onlyScaleDown()
            .into(holder.itemView.findViewById<ImageView>(R.id.show_user_image))


        holder.itemView.findViewById<CardView>(R.id.cardview_employee).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}