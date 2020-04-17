package com.example.lab6.ui.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lab6.R

class SearchFragment : Fragment() {

    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    private lateinit var navController: NavController
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.search_fragment, container, false)

        searchButton = root.findViewById(R.id.searchButton)
        searchEditText = root.findViewById(R.id.searchInput)

        searchButton.setOnClickListener {
            searchParks()
        }

        return root
    }

    private fun searchParks() {
        val searchTerm = searchEditText.text.toString()
        if(searchTerm != null && searchTerm != "") {
            sharedSearchViewModel.searchUserInput.value = searchTerm

            navController.navigate(R.id.action_navigation_search_to_searchResultsFragment)
        }
    }
}
