package com.netguru.shoppinglist.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.netguru.shoppinglist.app.Constants
import com.netguru.shoppinglist.app.data.model.converter.ShoppingItemConverters
import com.netguru.shoppinglist.app.data.dao.ShoppingListsDao
import com.netguru.shoppinglist.app.data.model.ShoppingList

@Database(entities = [ShoppingList::class], version = 2, exportSchema = true)
@TypeConverters(ShoppingItemConverters::class)
abstract class ShoppingListsDatabase : RoomDatabase() {
    abstract fun getShoppingListDao(): ShoppingListsDao

    companion object {
        private var instance: ShoppingListsDatabase? = null

        fun getInstance(context: Context): ShoppingListsDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ShoppingListsDatabase::class.java, Constants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

        fun deleteInstanceOfDatabase(){
            instance = null
        }
    }
}