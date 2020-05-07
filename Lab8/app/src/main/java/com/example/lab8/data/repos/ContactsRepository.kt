package com.example.lab8.data.repos

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lab8.data.database.Contacts
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactsRepository(val app: Application) {
    private val db = Firebase.firestore

    val contactsList = MutableLiveData<List<Contacts>>()
    private val allData: MutableMap<Int, Contacts> = mutableMapOf()

    init {
        db.collection("contacts")
            .addSnapshotListener { snapshot, e ->
                if(e != null) {
                    Log.e("data", "Listen failed.", e)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    parseAllData(snapshot)
                } else {
                    Log.d("data", "Current data: null")
                }
            }
    }

    private fun parseAllData(result: QuerySnapshot) {
        val allContacts = mutableListOf<Contacts>()
        for(doc in result) {
            val contact_id: Int = doc.id.toInt()
            val name: String = doc.getString("name")!!
            val number: Int = doc.getLong("number")!!.toInt()

            allContacts.add(
                Contacts(
                contact_id,
                name,
                number
            ))

            allData[contact_id] = Contacts(
                contact_id,
                name,
                number
            )
        }

        Log.i("Data", "allData: $allData")

        contactsList.value = allContacts
    }

    fun addContact(contact: Contacts) {
        val contactMap = contactToHashMap(contact)

        db.collection("contacts").document(contact.contact_id.toString())
            .set(contactMap)
            .addOnSuccessListener {
                Log.i("Error", "Added favorite success!")
            }
            .addOnFailureListener { exception ->
                Log.w("Error", "Error adding document.", exception)
            }
    }

    fun deleteContact(contact: Contacts) {
        db.collection("contacts").document(contact.contact_id.toString()).delete()
    }

    private fun contactToHashMap(contact: Contacts): HashMap<String, *> {
        val map = hashMapOf(
            "contact_id" to contact.contact_id,
            "name" to contact.name,
            "number" to contact.number
        )
        return map
    }

    val contacts: MutableLiveData<Contacts> = MutableLiveData()
}