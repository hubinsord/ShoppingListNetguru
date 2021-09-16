package com.netguru.shoppinglistnetguru.app.data.dao

import androidx.room.*
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList

@Dao
interface ShoppingListsDao {

    @Insert
    suspend fun insert(shoppingList: ShoppingList): Long

    @Update
    fun update(shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_lists_table")
    suspend fun getAllShoppingLists(): List<ShoppingList>

    @Query("DELETE  FROM shopping_lists_table " )
    fun deleteAllRecords()
}