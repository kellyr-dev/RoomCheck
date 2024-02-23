package com.example.userroom.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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


class ListFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel
    private lateinit var userRecycler: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var serchView : SearchView

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


        // Setting a TextWatcher to listener Material SearchView
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // this function is called before text is edited
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchUserOnDatabase(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                searchUserOnDatabase(s.toString())
            }
        }

        // SearchView
        serchView = view.findViewById(R.id.search_view)
        serchView.editText.addTextChangedListener(textWatcher)

        return view
    }

    /* old SearchView use OnQueryTextChange to listener queries

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
    }*/


    private fun searchUserOnDatabase(query: String){

        val searchQuery = "%$query%"
        userViewModel.searchUser(searchQuery).observe(viewLifecycleOwner) { listUsers ->

            listUsers.let {
                userAdapter = UserAdapter(it)
                userRecycler.adapter = userAdapter
            }

        }

    }

}