package com.example.lab7.data.repos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab7.data.database.AppDatabase
import com.example.lab7.data.database.Contacts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsRepository(val app: Application) {
    private val db = AppDatabase.getDatabase(app)

    private val contactsDAO = db.contactsDAO()

    val contactsRoomList: LiveData<List<Contacts>> = contactsDAO.getAllContacts()

    fun addContact(contact: Contacts) {
        CoroutineScope(Dispatchers.IO).launch {
            contactsDAO.insertContact(contact)
        }
    }

    fun deleteContact(contact: Contacts) {
        CoroutineScope(Dispatchers.IO).launch {
            contactsDAO.deleteContact(contact)
        }
    }

    val contacts: MutableLiveData<Contacts> = MutableLiveData()
}