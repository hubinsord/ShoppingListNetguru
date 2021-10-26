package com.netguru.shoppinglistnetguru.app.data.repository

import com.netguru.shoppinglistnetguru.app.data.dao.ShoppingListsDao
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.domain.ShoppingListRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class ShoppingListsRepositoryImpl @Inject constructor(private val shoppingListsDao: ShoppingListsDao) :
    ShoppingListRepository {

    override suspend fun insertShoppingList(shoppingList: ShoppingList) =
        shoppingListsDao.insert(shoppingList)

    override suspend fun updateShoppingList(shoppingList: ShoppingList) =
        CoroutineScope(Dispatchers.IO).launch {
            shoppingListsDao.update(shoppingList)
        }

    fun deleteShoppingList(shoppingList: ShoppingList) = CoroutineScope(Dispatchers.IO).launch {
        shoppingListsDao.delete(shoppingList)
    }

    override suspend fun getAllShoppingLists(isArchived: Boolean): List<ShoppingList> =
        shoppingListsDao.getAllShoppingLists(isArchived)

    fun deleteAllRecords() = CoroutineScope(Dispatchers.IO).launch {
        shoppingListsDao.deleteAllRecords()
    }
}