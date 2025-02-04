package com.example.userroom.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.userroom.R
import com.example.userroom.data.model.User
import com.example.userroom.mvvm.UserViewModel
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var addButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        addButton = view.findViewById<Button>(R.id.add_btn)
        addButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = view?.findViewById<EditText>(R.id.edit_name)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.edit_lastname)?.text.toString()
        val email = view?.findViewById<EditText>(R.id.edit_email)?.text.toString()
        val age = view?.findViewById<EditText>(R.id.edit_pass)?.text.toString()

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty() || TextUtils.isEmpty(age)) {
            Toast.makeText(requireContext(), "There is a field empty", Toast.LENGTH_SHORT).show()
        } else {
            // Create User Object
            val userObject = User(0, firstName, lastName, email, Integer.parseInt(age.toString()))

            // add User to ViewModel
            userViewModel.addUser(userObject)
            Snackbar.make(requireContext(), addButton, "User created sucessfully!", Snackbar.LENGTH_SHORT).show()

            // Navigate Back to List Fragment and see the update in Recycler View
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }
}
