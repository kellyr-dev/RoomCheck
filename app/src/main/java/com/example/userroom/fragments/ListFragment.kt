package com.example.userroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userroom.R
import com.example.userroom.adapters.UserAdapter
import com.example.userroom.mvvm.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.search.SearchView


class ListFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var userViewModel : UserViewModel
    private lateinit var userRecycler: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val floatButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Recycler
        userRecycler = view.findViewById(R.id.list_rv)
        userRecycler.layoutManager = LinearLayoutManager(requireContext())
        userRecycler.setHasFixedSize(true)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.observeUsers().observe(viewLifecycleOwner, Observer { user ->

            userAdapter = UserAdapter(user)
            userRecycler.adapter = userAdapter

        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.seach_menu)?.actionView as androidx.appcompat.widget.SearchView?

        searchView?.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchUserOnDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            searchUserOnDatabase(newText)
        }
        return true
    }


    private fun searchUserOnDatabase(query: String){

        val searchQuery = "%$query%"
        userViewModel.searchUser(searchQuery).observe(viewLifecycleOwner) { listUsers ->

            listUsers.let {
                userAdapter = UserAdapter(it)
            }

        }

    }

}