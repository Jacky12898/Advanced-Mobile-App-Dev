package com.example.lab6.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.lab6.R

class DetailFragment : Fragment() {
    private lateinit var sharedSearchViewModel: SharedSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = android.view.View.GONE

        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val name = root.findViewById<TextView>(R.id.nameTextView)
        val description = root.findViewById<TextView>(R.id.descriptionTextView)
        val directions = root.findViewById<TextView>(R.id.directionsTextView)

        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        sharedSearchViewModel.selectedPark.observe(viewLifecycleOwner, Observer{
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.name
            name.text = it.name
            description.text = it.description
            directions.text = it.directionsUrl
        })
        return root
    }
}