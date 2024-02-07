package com.example.userroom.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userroom.R
import com.example.userroom.data.model.User
import com.example.userroom.mvvm.UserViewModel
import kotlinx.coroutines.launch


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<TextView>(R.id.edit_name).text = args.updateUser.firstName
        view.findViewById<TextView>(R.id.edit_lastname).text = args.updateUser.lastName
        view.findViewById<TextView>(R.id.text_email_toupdate).text = args.updateUser.email
        view.findViewById<TextView>(R.id.edit_age).text = args.updateUser.age.toString()

        val buttonUpdate = view.findViewById<Button>(R.id.update_btn)
        buttonUpdate.setOnClickListener {
            updateUser()
        }
        return view
    }

    private fun updateUser() {
        val fname = view?.findViewById<TextView>(R.id.edit_name)!!.text.toString()
        val lname = view?.findViewById<TextView>(R.id.edit_lastname)!!.text.toString()
        val age = Integer.parseInt(view?.findViewById<TextView>(R.id.edit_age)!!.text.toString())

        if (fname.isEmpty() || lname.isEmpty() || age.toString().isEmpty()){
            Toast.makeText(requireContext(), "One of the field is empty", Toast.LENGTH_SHORT).show()
        }else{
            // step to update an User
            // a) create User Object keeping the same Id
            // b) call method in ViewModel to keep the data
            // c) go back to the fragment List
            val updatingUser = User(args.updateUser.id, fname, lname, args.updateUser.email, age)
            userViewModel.updateUser(updatingUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

}