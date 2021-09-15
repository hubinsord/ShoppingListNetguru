package com.netguru.shoppinglistnetguru.shoppinglists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netguru.data.model.ShoppingItem
import com.netguru.data.model.ShoppingList

class ShoppingListsViewModel : ViewModel(){
    private var shoppingLists = mutableListOf<ShoppingList>()
     var shoppingListsLiveData = MutableLiveData<MutableList<ShoppingList>>()

    private fun updateShoppingItems(shoppingList: MutableList<ShoppingList>){
       this.shoppingListsLiveData.postValue(shoppingList)
    }

    fun addNewShoppingList(newShoppingList: ShoppingList){
        shoppingLists.add(newShoppingList)
        updateShoppingItems(shoppingLists)
    }
}
