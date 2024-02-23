package com.example.userroom.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
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
    private lateinit var toolbar2 : Toolbar

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // initialization of a toolbar
        toolbar2 = view.findViewById(R.id.myTopToolbar)

        view.findViewById<TextView>(R.id.name_detail).text = args.currentUser.firstName + " " + args.currentUser.lastName
        view.findViewById<TextView>(R.id.age_detail).text = args.currentUser.age.toString()
        view.findViewById<TextView>(R.id.pid_detail).text = args.currentUser.id.toString()
        view.findViewById<TextView>(R.id.email_detail).text = args.currentUser.email.toString()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Add menu to Toolbar
        setToolbarMenu()

        return view
    }

    private fun setToolbarMenu() {

        toolbar2.inflateMenu(R.menu.options_menu)
        toolbar2.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(arg0: MenuItem): Boolean {
                if (arg0.itemId == R.id.delete_menu) {
                    showDialog()
                }

                if (arg0.itemId == R.id.update_menu){
                    val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.currentUser)
                    findNavController().navigate(action)
                }
                return false

            }
        })
    }

    private fun showDialog(): Boolean {
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
        return true
    }

    private fun deleteUser(user: User) {
        userViewModel.deleteUser(user)
        Toast.makeText(requireContext(), "Successfully removed: ${user.firstName}", Toast.LENGTH_SHORT).show()
    }

}