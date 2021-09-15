package com.netguru.shoppinglistnetguru.shoppinglistdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.netguru.shoppinglistnetguru.shoppinglists.ShoppingListsViewModel

class ShoppingListDetailViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListDetailsViewModel::class.java))
            return ShoppingListDetailsViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}