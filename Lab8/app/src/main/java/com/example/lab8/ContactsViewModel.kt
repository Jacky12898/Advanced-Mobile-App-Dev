package com.example.lab8

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab8.data.database.Contacts
import com.example.lab8.data.repos.ContactsRepository

class ContactsViewModel (app: Application) : AndroidViewModel(app) {
    private val contactsRepo = ContactsRepository(app)

    val contactList: MutableLiveData<List<Contacts>> = contactsRepo.contactsList

    fun addContact(contact: Contacts) {
        contactsRepo.addContact(contact)
    }

    fun deleteContact(contact: Contacts) {
        contactsRepo.deleteContact(contact)
    }
}