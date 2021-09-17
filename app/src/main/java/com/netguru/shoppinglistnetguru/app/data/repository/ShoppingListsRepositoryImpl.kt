package com.netguru.shoppinglistnetguru.app.data.repository

import android.app.Application
import com.netguru.shoppinglistnetguru.app.data.dao.ShoppingListsDao
import com.netguru.shoppinglistnetguru.app.data.db.ShoppingListsDatabase
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.app.domain.ShoppingListRepository
import kotlinx.coroutines.*

class ShoppingListsRepositoryImpl(application: Application) : ShoppingListRepository {
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

    override suspend fun getAllShoppingLists(isArchived: Int): List<ShoppingList> =
        shoppingListsDao.getAllShoppingLists(isArchived)

    fun deleteAllRecords() = CoroutineScope(Dispatchers.IO).launch {
        shoppingListsDao.deleteAllRecords()
    }


}