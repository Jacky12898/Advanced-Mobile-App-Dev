package com.example.lab7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab7.data.database.Contacts
import com.example.lab7.data.repos.ContactsRepository

class ContactsViewModel (app: Application) : AndroidViewModel(app) {
    private val contactsRepo = ContactsRepository(app)

    val contactList: MutableLiveData<List<Contacts>> = MutableLiveData()

    private val contactListObserver =  Observer<List<Contacts>> {
        val allContacts = mutableListOf<Contacts>()

        for(contact in it) {
            allContacts.add(contact)
        }
        contactList.value = allContacts
    }

    init {
        contactsRepo.contactsRoomList.observeForever(contactListObserver)
    }

    override fun onCleared() {
        contactsRepo.contactsRoomList.removeObserver(contactListObserver)
        super.onCleared()
    }

    fun addContact(contact: Contacts) {
        contactsRepo.addContact(contact)
    }

    fun deleteContact(contact: Contacts) {
        contactsRepo.deleteContact(contact)
    }
}