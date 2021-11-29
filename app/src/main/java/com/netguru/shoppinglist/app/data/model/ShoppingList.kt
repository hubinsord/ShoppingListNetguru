package com.netguru.shoppinglist.app.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(tableName = "shopping_lists_table")
@Parcelize
data class ShoppingList(
    val name: String,
    var items: @RawValue MutableList<ShoppingItem>,

    @ColumnInfo(name = "is_archived")
    var isArchived: Boolean = false
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}