package com.example.lab7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lab7.data.database.Contacts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactsFragment : Fragment(), ContactsRecyclerAdapter.ContactItemListener {
    private lateinit var contactsVM: ContactsViewModel
    private lateinit var contactsRecyclerView: RecyclerView
    private lateinit var adapter: ContactsRecyclerAdapter
    private lateinit var navController: NavController
    private lateinit var actionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactsVM =
            ViewModelProvider(requireActivity()).get(ContactsViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_contacts, container, false)
        contactsRecyclerView = root.findViewById(R.id.contactsRecyclerView)

        adapter = ContactsRecyclerAdapter(requireContext(), emptyList<Contacts>(), this)
        contactsRecyclerView.adapter = adapter

        contactsVM.contactList.observe(viewLifecycleOwner, Observer {
            adapter.contactsList = it
            adapter.notifyDataSetChanged()
        })

        actionButton = root.findViewById(R.id.fab)
        actionButton.setOnClickListener{
            navController.navigate(R.id.)
        }

        return root
    }

    // TODO: Program delete on hold down
    override fun onContactItemClick(contacts: Contacts) {
        contactsVM.contactSelected(contacts)
        navController.navigate(R.id.)
    }
}
