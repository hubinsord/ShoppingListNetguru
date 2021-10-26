package com.netguru.shoppinglistnetguru.app.di

import android.content.Context
import androidx.room.Room
import com.netguru.shoppinglistnetguru.app.Constants
import com.netguru.shoppinglistnetguru.app.data.db.ShoppingListsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingListsDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingListsDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideShoppingListDao(database: ShoppingListsDatabase) =
        database.getShoppingListDao()
}