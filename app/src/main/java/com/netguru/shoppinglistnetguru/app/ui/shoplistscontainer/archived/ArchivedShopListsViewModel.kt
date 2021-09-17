package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.archived

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.data.repository.ShoppingListsRepositoryImpl
import kotlinx.coroutines.launch

class ArchivedShopListsViewModel(application: Application): AndroidViewModel(application) {

    var shoppingListsLiveData = MutableLiveData<MutableList<ShoppingList>>()
    private var shoppingListsRepository: ShoppingListsRepositoryImpl =
        ShoppingListsRepositoryImpl(application)

    fun getAllShoppingLists(isArchived: Boolean) {
        viewModelScope.launch {
            val data = shoppingListsRepository.getAllShoppingLists(isArchived)
            shoppingListsLiveData.postValue(data.toMutableList())
        }
    }
}