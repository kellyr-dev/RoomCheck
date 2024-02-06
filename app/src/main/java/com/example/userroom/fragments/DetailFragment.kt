package com.example.userroom.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.userroom.R


class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

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

        return view
    }


}