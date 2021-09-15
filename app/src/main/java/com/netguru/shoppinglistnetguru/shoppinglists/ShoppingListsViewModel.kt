package com.netguru.shoppinglistnetguru.shoppinglists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netguru.data.model.ShoppingItem

class ShoppingListsViewModel : ViewModel(){
    private var shoppingLists = mutableListOf(mutableListOf<ShoppingItem>())
     var shoppingListsLiveData = MutableLiveData<MutableList<MutableList<ShoppingItem>>>()

    fun updateShoppingItems(shoppingItems: MutableList<MutableList<ShoppingItem>>){
       this.shoppingListsLiveData.postValue(shoppingItems)
    }

    fun addNewShoppingList(newShoppingList: MutableList<ShoppingItem>){
        shoppingLists.add(newShoppingList)
        updateShoppingItems(shoppingLists)
    }
}
