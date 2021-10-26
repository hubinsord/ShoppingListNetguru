package com.netguru.shoppinglist.app.domain

import com.netguru.shoppinglist.app.data.model.ShoppingList
import kotlinx.coroutines.Job

interface ShoppingListRepository {
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long
    suspend fun updateShoppingList(shoppingList: ShoppingList): Job
    suspend fun getAllShoppingLists(isArchived: Boolean): List<ShoppingList>
}