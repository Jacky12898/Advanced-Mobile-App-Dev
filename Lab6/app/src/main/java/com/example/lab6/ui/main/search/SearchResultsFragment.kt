package com.example.lab6.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.LOG_TAG
import com.example.lab6.R
import com.example.lab6.data.Park

class SearchResultsFragment : Fragment(), SearchRecyclerAdapter.ParkItemListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        val root = inflater.inflate(R.layout.search_results_fragment, container, false)

        recyclerView = root.findViewById(R.id.recyclerView)

        sharedSearchViewModel.parkData.observe(viewLifecycleOwner, Observer {
            val adapter = SearchRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
        })

        return root
    }

    override fun onParkItemClick(park: Park) {
        Log.i(LOG_TAG, park.toString())
        sharedSearchViewModel.selectedPark.value = park
        navController.navigate(R.id.action_searchResultsFragment_to_detail)
    }
}
