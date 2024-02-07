package com.example.userroom.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userroom.R
import com.example.userroom.data.model.User
import com.example.userroom.mvvm.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var userViewModel : UserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.findViewById<TextView>(R.id.name_detail).text = args.currentUser.firstName + " " + args.currentUser.lastName
        view.findViewById<TextView>(R.id.age_detail).text = args.currentUser.age.toString()
        view.findViewById<TextView>(R.id.pid_detail).text = args.currentUser.id.toString()
        view.findViewById<TextView>(R.id.email_detail).text = args.currentUser.email.toString()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu){
            showDialog()
        }

        if (item.itemId == R.id.update_menu){
            val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.currentUser)
            findNavController().navigate(action)
        }

        /*
        holder.itemView.findViewById<CardView>(R.id.cardview_employee).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }
         */

        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
       MaterialAlertDialogBuilder(requireContext())
           .setTitle("Delete User")
           .setMessage("Do you want to remove to "+args.currentUser.firstName+"?")
           .setNegativeButton("Cancel"){
               dialog, which ->

           }
           .setPositiveButton("Accept"){
               dialog, which -> deleteUser(args.currentUser)
               findNavController().navigate(R.id.action_detailFragment_to_listFragment)
           }
           .show()
    }

    private fun deleteUser(user: User) {
        userViewModel.deleteUser(user)
        Toast.makeText(requireContext(), "Successfully removed: ${user.firstName}", Toast.LENGTH_SHORT).show()
    }

}