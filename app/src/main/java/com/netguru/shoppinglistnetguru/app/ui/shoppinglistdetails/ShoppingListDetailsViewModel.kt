package com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.data.repository.ShoppingListsRepositoryImpl

class ShoppingListDetailsViewModel(application: Application): AndroidViewModel(application) {

    var shoppingListLiveData = MutableLiveData<ShoppingList>()
    private lateinit var shoppingList : ShoppingList

    private var shoppingListsRepository: ShoppingListsRepositoryImpl =
        ShoppingListsRepositoryImpl(application)

    fun updateShoppingList(shoppingList: ShoppingList) {
        this.shoppingList = shoppingList
        shoppingListLiveData.postValue(shoppingList)

    }

    fun addNewProduct(shoppingItem: ShoppingItem) {
        shoppingList.items.add(shoppingItem)
        shoppingListLiveData.postValue(shoppingList)
        shoppingListsRepository.updateShoppingList(shoppingList)
    }

    fun archiveShoppingList(){
        shoppingList.isArchived = true
        updateShoppingList(shoppingList)
        shoppingListsRepository.updateShoppingList(shoppingList)
    }
}