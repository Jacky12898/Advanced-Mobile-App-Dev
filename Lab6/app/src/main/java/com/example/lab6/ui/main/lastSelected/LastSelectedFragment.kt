package com.example.lab6.ui.main.lastSelected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lab6.R
import com.example.lab6.ui.main.search.SharedSearchViewModel
import kotlinx.android.synthetic.main.last_selected_fragment.*

class LastSelectedFragment : Fragment() {
    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    private lateinit var lastSelectedViewModel: LastSelectedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        sharedSearchViewModel.selectedPark.observe(viewLifecycleOwner, Observer{
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.name
            lastSelectedText.text = it.name
        })
        lastSelectedViewModel =
            ViewModelProvider(this).get(LastSelectedViewModel::class.java)
        val root = inflater.inflate(R.layout.last_selected_fragment, container, false)

        val textView: TextView = root.findViewById(R.id.lastSelectedText)
        lastSelectedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}