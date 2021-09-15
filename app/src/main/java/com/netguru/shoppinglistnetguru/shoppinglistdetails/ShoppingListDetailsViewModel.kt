package com.netguru.shoppinglistnetguru.shoppinglistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netguru.data.model.ShoppingItem
import com.netguru.data.model.ShoppingList

class ShoppingListDetailsViewModel: ViewModel() {

    private lateinit var shoppingList : ShoppingList
    var shoppingListLiveData = MutableLiveData<ShoppingList>()

    fun updateShoppingList(shoppingList: ShoppingList) {
        this.shoppingList = shoppingList
        shoppingListLiveData.postValue(shoppingList)
    }

    fun addNewItem(shoppingItem: ShoppingItem) {
        shoppingList.items.add(shoppingItem)
        shoppingListLiveData.postValue(shoppingList)
    }
}