package com.netguru.shoppinglist.app.ui.shoppinglistdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.shoppinglist.app.data.model.ShoppingItem
import com.netguru.shoppinglist.app.data.model.ShoppingList
import com.netguru.shoppinglist.app.data.repository.ShoppingListsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListDetailsViewModel @Inject constructor(private val shoppingListsRepository: ShoppingListsRepositoryImpl) :
    ViewModel() {

    var shoppingListLiveData = MutableLiveData<ShoppingList>()
    private lateinit var shoppingList: ShoppingList

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