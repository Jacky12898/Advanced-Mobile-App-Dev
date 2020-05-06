package com.example.project2.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlin.collections.List

@Dao
interface ShoppingListDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingListItem(shoppingList: ShoppingList)

    @Delete()
    fun deleteShoppingListItem(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list_table")
    fun getAllShoppingListItems(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list_table WHERE shopping_list_id = :id")
    fun getShoppingListItem(id: Int): ShoppingList
}