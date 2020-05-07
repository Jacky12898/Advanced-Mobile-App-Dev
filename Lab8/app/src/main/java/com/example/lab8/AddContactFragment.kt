package com.example.lab8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lab8.data.database.Contacts
import com.google.android.material.snackbar.Snackbar

class AddContactFragment: Fragment(){
    private lateinit var navController: NavController
    private lateinit var saveButton: Button
    private lateinit var contactsVM: ContactsViewModel
    private lateinit var name: EditText
    private lateinit var number: EditText
    private lateinit var newContact: Contacts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactsVM =
            ViewModelProvider(requireActivity()).get(ContactsViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_add_contact, container, false)

        name = root.findViewById(R.id.nameText)
        number = root.findViewById(R.id.numberText)
        saveButton = root.findViewById(R.id.saveButton)

        saveButton.setOnClickListener{

            if(name.text.toString() == "" || number.text.toString() == "") {
                val snack = Snackbar.make(it,"Fill out all fields",Snackbar.LENGTH_LONG)
                snack.show()
            }

            else{
                newContact = Contacts((0 until 1000).random(), name.text.toString(), number.text.toString().toInt())
                contactsVM.addContact(newContact)
                navController.navigate(R.id.action_add_contact_to_contacts)
            }
        }

        return root
    }
}