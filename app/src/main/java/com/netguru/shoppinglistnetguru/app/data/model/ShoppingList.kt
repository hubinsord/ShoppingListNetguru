package com.netguru.shoppinglistnetguru.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists_table")
data class ShoppingList(
    val name: String,
    var items: MutableList<ShoppingItem>,

    @ColumnInfo(name = "is_archived")
    var isArchived: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}