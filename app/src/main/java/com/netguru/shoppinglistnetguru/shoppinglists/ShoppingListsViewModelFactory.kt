package com.netguru.shoppinglistnetguru.shoppinglists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.netguru.shoppinglistnetguru.MainViewModel

class ShoppingListsViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShoppingListsViewModel::class.java))
            return ShoppingListsViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}