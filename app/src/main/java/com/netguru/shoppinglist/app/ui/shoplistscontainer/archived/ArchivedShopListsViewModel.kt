package com.netguru.shoppinglist.app.ui.shoplistscontainer.archived

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.shoppinglist.app.data.model.ShoppingList
import com.netguru.shoppinglist.app.data.repository.ShoppingListsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivedShopListsViewModel @Inject constructor(private val repository: ShoppingListsRepositoryImpl):
    ViewModel() {

    var shoppingListsLiveData = MutableLiveData<MutableList<ShoppingList>>()

    fun getAllShoppingLists(isArchived: Boolean) {
        viewModelScope.launch {
            val data = repository.getAllShoppingLists(isArchived)
            shoppingListsLiveData.postValue(data.toMutableList())
        }
    }
}