package com.netguru.shoppinglistnetguru.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists_table")
data class ShoppingList(
    val name: String,
    var items: MutableList<ShoppingItem>,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}