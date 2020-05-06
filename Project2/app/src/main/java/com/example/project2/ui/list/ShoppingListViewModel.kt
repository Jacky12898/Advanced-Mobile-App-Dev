package com.example.project2.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.project2.data.database.ShoppingList
import com.example.project2.data.repos.ShoppingListRepository

class ShoppingListViewModel (app: Application) : AndroidViewModel(app) {
    private val shoppingListRepo = ShoppingListRepository(app)

    val shoppingList: MutableLiveData<List<ShoppingList>> = MutableLiveData()

    private val shoppingListObserver =  Observer<List<ShoppingList>> {
        val allShoppingListItems = mutableListOf<ShoppingList>()

        for(shoppingListItem in it) {
            allShoppingListItems.add(shoppingListItem)
        }
        shoppingList.value = allShoppingListItems
    }

    init {
        shoppingListRepo.shoppingListRoomList.observeForever(shoppingListObserver)
    }

    override fun onCleared() {
        shoppingListRepo.shoppingListRoomList.removeObserver(shoppingListObserver)
        super.onCleared()
    }

    fun addShoppingListItem(shoppingListItem: ShoppingList) {
        shoppingListRepo.addShoppingListItem(shoppingListItem)
    }

    fun deleteShoppingListItem(shoppingListItem: ShoppingList) {
        shoppingListRepo.deleteShoppingListItem(shoppingListItem)
    }
}