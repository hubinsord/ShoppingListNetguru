package com.netguru.shoppinglistnetguru.app.data.repository

import android.app.Application
import com.netguru.shoppinglistnetguru.app.data.dao.ShoppingListsDao
import com.netguru.shoppinglistnetguru.app.data.db.ShoppingListsDatabase
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.domain.IShoppingListRepository
import kotlinx.coroutines.*

class ShoppingListsRepository(application: Application) : IShoppingListRepository {
    private var shoppingListsDao: ShoppingListsDao

    init {
        val database = ShoppingListsDatabase.getInstance(application.applicationContext)
        shoppingListsDao = database!!.shoppingListDao()
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList) =
        shoppingListsDao.insert(shoppingList)

    override fun updateShoppingList(shoppingList: ShoppingList) =
        CoroutineScope(Dispatchers.IO).launch {
            shoppingListsDao.update(shoppingList)
        }

    fun deleteShoppingList(shoppingList: ShoppingList) = CoroutineScope(Dispatchers.IO).launch {
        shoppingListsDao.delete(shoppingList)
    }

    override suspend fun getAllShoppingLists(): List<ShoppingList> =
        shoppingListsDao.getAllShoppingLists()

    fun deleteAllRecords() = CoroutineScope(Dispatchers.IO).launch {
        shoppingListsDao.deleteAllRecords()
    }
}