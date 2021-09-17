package com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.data.repository.ShoppingListsRepositoryImpl
import kotlinx.coroutines.launch

class ShoppingListDetailsViewModel(application: Application) : AndroidViewModel(application) {

    var shoppingListLiveData = MutableLiveData<ShoppingList>()
    private lateinit var shoppingList: ShoppingList

    private var shoppingListsRepository: ShoppingListsRepositoryImpl =
        ShoppingListsRepositoryImpl(application)

    fun updateShoppingList(shoppingList: ShoppingList) {
        this.shoppingList = shoppingList
        shoppingListLiveData.postValue(shoppingList)
    }

    fun addNewProduct(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingList.items.add(shoppingItem)
            shoppingListLiveData.postValue(shoppingList)
            shoppingListsRepository.updateShoppingList(shoppingList)
        }
    }

    fun archiveShoppingList() {
        viewModelScope.launch {
            shoppingList.isArchived = true
            shoppingListsRepository.updateShoppingList(shoppingList)
            updateShoppingList(shoppingList)
        }
    }

    fun updateProduct(product: ShoppingItem, position: Int) {
        viewModelScope.launch {
            shoppingList.items[position] = product
            shoppingListsRepository.updateShoppingList(shoppingList)
            updateShoppingList(shoppingList)
        }
    }
}