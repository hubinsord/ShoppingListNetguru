package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active

import androidx.lifecycle.*
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.data.repository.ShoppingListsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActiveShopListsViewModel @Inject constructor(private val repository: ShoppingListsRepositoryImpl) :
    ViewModel() {
    var shoppingListsLiveData = MutableLiveData<MutableList<ShoppingList>>()

    fun addNewShoppingList(newShoppingList: ShoppingList) {
        viewModelScope.launch {
            val id = insertShoppingList(newShoppingList)
            newShoppingList.id = id.toInt()
            shoppingListsLiveData.postValue(shoppingListsLiveData.value?.apply {
                add(newShoppingList)
            })
        }
    }

    fun getAllShoppingLists(isArchived: Boolean) {
        viewModelScope.launch {
            val data = repository.getAllShoppingLists(isArchived)
            shoppingListsLiveData.postValue(data.toMutableList())
        }
    }

    private suspend fun insertShoppingList(shoppingList: ShoppingList): Long {
        return repository.insertShoppingList(shoppingList)
    }
}
