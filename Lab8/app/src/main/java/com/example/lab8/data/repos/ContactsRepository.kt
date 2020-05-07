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
        db.collection("favorites")
            .addSnapshotListener { snapshot, e ->
                if(e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    parseAllData(snapshot)
                }
            }
    }

    private fun parseAllData(result: QuerySnapshot) {
        val allContacts = mutableListOf<Contacts>()
        for(doc in result) {
            val id: Int = doc.id.toInt()
            val name: String = doc.getString("name")!!
            val number: Int = (doc.get("number") as String).toInt()

            allContacts.add(
                Contacts(
                id,
                name,
                number
            ))

            allData[id] = Contacts(
                id,
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