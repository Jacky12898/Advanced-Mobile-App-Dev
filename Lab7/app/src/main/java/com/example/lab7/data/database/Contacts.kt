package com.example.lab7.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contacts (
    @PrimaryKey val contact_id: Int,
    val name: String,
    val number: Int
)