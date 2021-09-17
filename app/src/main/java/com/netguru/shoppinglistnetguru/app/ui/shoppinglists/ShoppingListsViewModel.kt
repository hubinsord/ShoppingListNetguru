package com.netguru.shoppinglistnetguru.app.ui.shoppinglists

import android.app.Application
import androidx.lifecycle.*
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.data.repository.ShoppingListsRepositoryImpl
import kotlinx.coroutines.launch

class ShoppingListsViewModel(application: Application) : AndroidViewModel(application) {
    var shoppingListsLiveData = MutableLiveData<MutableList<ShoppingList>>()

    private var shoppingListsRepository: ShoppingListsRepositoryImpl =
        ShoppingListsRepositoryImpl(application)

    fun addNewShoppingList(newShoppingList: ShoppingList) {
        viewModelScope.launch {
            val id = insertShoppingList(newShoppingList)
            newShoppingList.id = id.toInt()
            shoppingListsLiveData.postValue(shoppingListsLiveData.value?.apply {
                add(newShoppingList)
            })
        }
    }

    fun getAllShoppingLists(isArchived: Int) {
        viewModelScope.launch {
            val data = shoppingListsRepository.getAllShoppingLists(isArchived)
            shoppingListsLiveData.postValue(data.toMutableList())
        }
    }

    private suspend fun insertShoppingList(shoppingList : ShoppingList): Long {
        return shoppingListsRepository.insertShoppingList(shoppingList)
    }
}
