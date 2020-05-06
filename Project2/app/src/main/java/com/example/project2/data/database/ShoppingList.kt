package com.example.project2.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class ShoppingList (
    @PrimaryKey val shopping_list_id: Int,
    val label: String,
    val url: String
)