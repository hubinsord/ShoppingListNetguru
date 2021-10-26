package com.netguru.shoppinglist.app.data.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.netguru.shoppinglist.app.data.model.ShoppingItem

class ShoppingItemConverters {
    @TypeConverter
    fun fromGroupTaskMemberList(value: List<ShoppingItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ShoppingItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<ShoppingItem> {
        val gson = Gson()
        val type = object : TypeToken<List<ShoppingItem>>() {}.type
        return gson.fromJson(value, type)
    }
}