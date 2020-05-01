package com.example.lab7.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contacts: Contacts)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("SELECT * FROM contacts_table WHERE contact_id = :id")
    fun getContact(id: Int): Contacts
}