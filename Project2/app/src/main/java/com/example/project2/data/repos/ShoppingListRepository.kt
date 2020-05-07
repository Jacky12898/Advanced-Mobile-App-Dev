package com.example.project2.data.repos

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project2.data.database.AppDatabase
import com.example.project2.data.database.ShoppingList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListRepository(val app: Application) {
    private val db = AppDatabase.getDatabase(app)

    private val shoppingListDAO = db.shoppingListDAO()

    val shoppingListRoomList: LiveData<List<ShoppingList>> = shoppingListDAO.getAllShoppingListItems()

    fun addShoppingListItem(shoppingListItem: ShoppingList) {
        CoroutineScope(Dispatchers.IO).launch {
            shoppingListDAO.insertShoppingListItem(shoppingListItem)
        }
    }

    fun deleteShoppingListItem(shoppingListItem: ShoppingList) {
        CoroutineScope(Dispatchers.IO).launch {
            shoppingListDAO.deleteShoppingListItem(shoppingListItem)
        }
    }
}