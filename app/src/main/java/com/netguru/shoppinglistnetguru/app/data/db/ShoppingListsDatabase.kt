package com.netguru.shoppinglistnetguru.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.netguru.shoppinglistnetguru.app.data.model.converter.ShoppingItemConverters
import com.netguru.shoppinglistnetguru.app.data.dao.ShoppingListsDao
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList

@Database(entities = [ShoppingList::class], version = 1, exportSchema = true)
@TypeConverters(ShoppingItemConverters::class)
abstract class ShoppingListsDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListsDao

    companion object {
        private var instance: ShoppingListsDatabase? = null

        fun getInstance(context: Context): ShoppingListsDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ShoppingListsDatabase::class.java,
                    "shopping_lists_table")
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